package com.abdullah.weatherapp.presenter.impl

import android.content.Context
import com.abdullah.weatherapp.WeatherApp
import com.abdullah.weatherapp.domain.interactor.CurrentWeatherInteractor
import com.abdullah.weatherapp.domain.service.GpsService
import com.abdullah.weatherapp.model.entity.Location
import com.abdullah.weatherapp.presenter.MainPresenter
import com.abdullah.weatherapp.view.MainView
import rx.android.schedulers.AndroidSchedulers
import rx.functions.Action1
import rx.schedulers.Schedulers
import rx.subscriptions.CompositeSubscription
import android.widget.Toast



/**
 * Created by abdullah on 24.1.2018.
 */
class MainPresenterImpl : MainPresenter {

    private var view: MainView? = null
    private var compositeSubscription: CompositeSubscription? = null
    private val interactor: CurrentWeatherInteractor
    private var gpsService: GpsService? = null

    constructor(currentWeatherInteractor: CurrentWeatherInteractor) {
        this.interactor = currentWeatherInteractor
    }

    override fun attachView(view: MainView, context: Context) {
        this.view = view
        compositeSubscription = CompositeSubscription()
        gpsService = GpsService(context)
        if (gpsService?.canGetLocation() == true) {

            val latitude = gpsService?.getLatitude()
            val longitude = gpsService?.getLongitude()

            getLastWeatherStateByLocation(Location(latitude?.toFloat(), longitude?.toFloat()))
        } else {
            gpsService?.showSettingsAlert()
        }
    }

    override fun detachView() {
        if (this.view != null) {
            view = null
            compositeSubscription?.unsubscribe()
            compositeSubscription = null
            gpsService?.stopUsingGPS()

        }
    }


    override fun getLastWeatherStateByLocation(location: Location) {
        compositeSubscription?.add(interactor.getCurrentWeatherServiceByLocation(location)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(Action1 { result -> view?.fillWeatherResult(result) }))
    }
}