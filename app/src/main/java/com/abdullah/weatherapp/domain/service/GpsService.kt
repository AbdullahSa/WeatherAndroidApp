package com.abdullah.weatherapp.domain.service

import android.annotation.SuppressLint
import android.app.Service
import android.content.Context
import android.content.Intent
import android.location.Location
import android.location.LocationListener
import android.os.Bundle
import android.os.IBinder
import android.location.LocationManager
import android.R.string.cancel
import android.app.AlertDialog
import android.content.DialogInterface
import android.provider.Settings
import android.support.v4.content.ContextCompat.startActivity
import android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS


/**
 * Created by abdullah on 29.1.2018.
 */
class GpsService : Service, LocationListener {

    private var mContext: Context? = null

    private var isGPSEnabled: Boolean? = false

    private var isNetworkEnabled: Boolean? = false

    private var canGetLocation: Boolean? = false

    private var location: Location? = null
    private var latitude: Double? = 0.toDouble()
    private var longitude: Double? = 0.toDouble()


    private val MIN_DISTANCE_CHANGE_FOR_UPDATES: Float = 10.toFloat()

    private val MIN_TIME_BW_UPDATES = (1000 * 60 * 1).toLong()

    protected var locationManager: LocationManager? = null

    constructor(context: Context) {
        this.mContext = context;
        getLocation()
    }

    @SuppressLint("MissingPermission")
    fun getLocation(): Location? {
        try {
            this.locationManager = mContext
                    ?.getSystemService(Context.LOCATION_SERVICE) as LocationManager

            // getting GPS status
            isGPSEnabled = locationManager?.isProviderEnabled(LocationManager.GPS_PROVIDER)

            // getting network status
            isNetworkEnabled = locationManager
                    ?.isProviderEnabled(LocationManager.NETWORK_PROVIDER)

            if (isGPSEnabled == false && isNetworkEnabled == false) {
                // no network provider is enabled
            } else {
                this.canGetLocation = true
                // First get location from Network Provider
                if (isNetworkEnabled == false) {
                    locationManager?.requestLocationUpdates(
                            LocationManager.NETWORK_PROVIDER,
                            MIN_TIME_BW_UPDATES,
                            MIN_DISTANCE_CHANGE_FOR_UPDATES, this)
                    if (locationManager != null) {
                        location = locationManager
                                ?.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)
                        if (location != null) {
                            latitude = location?.getLatitude()
                            longitude = location?.getLongitude()
                        }
                    }
                }
                // if GPS Enabled get lat/long using GPS Services
                if (isGPSEnabled == true) {
                    if (location == null) {
                        locationManager?.requestLocationUpdates(
                                LocationManager.GPS_PROVIDER,
                                MIN_TIME_BW_UPDATES,
                                MIN_DISTANCE_CHANGE_FOR_UPDATES, this)
                        if (locationManager != null) {
                            location = locationManager
                                    ?.getLastKnownLocation(LocationManager.GPS_PROVIDER)
                            if (location != null) {
                                latitude = location?.getLatitude()
                                longitude = location?.getLongitude()
                            }
                        }
                    }
                }
            }

        } catch (e: Exception) {
            e.printStackTrace()
        }

        return location
    }

    fun getLatitude(): Double? {
        if (location != null) {
            latitude = location?.getLatitude()
        }

        return latitude
    }

    fun getLongitude(): Double? {
        if (location != null) {
            longitude = location?.getLongitude()
        }

        return longitude
    }

    fun canGetLocation(): Boolean? {
        return this.canGetLocation
    }

    /**
     * Function to show settings alert dialog
     */
    fun showSettingsAlert() {
        val alertDialog = AlertDialog.Builder(mContext)

        alertDialog.setTitle("GPS is settings")

        alertDialog.setMessage("GPS is not enabled. Do you want to go to settings menu?")

        alertDialog.setPositiveButton("Settings", DialogInterface.OnClickListener { dialog, which ->
            val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
            mContext?.startActivity(intent)
        })

        alertDialog.setNegativeButton("Cancel", DialogInterface.OnClickListener { dialog, which -> dialog.cancel() })

        alertDialog.show()
    }

    fun stopUsingGPS() {
        if (locationManager != null) {
            locationManager?.removeUpdates(this@GpsService)
        }
    }

    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    override fun onLocationChanged(p0: Location?) {

    }

    override fun onStatusChanged(p0: String?, p1: Int, p2: Bundle?) {

    }

    override fun onProviderEnabled(p0: String?) {

    }

    override fun onProviderDisabled(p0: String?) {

    }
}