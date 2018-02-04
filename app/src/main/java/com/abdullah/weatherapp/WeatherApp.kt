package com.abdullah.weatherapp

import android.app.Application
import com.abdullah.weatherapp.domain.di.DaggerNetworkComponent
import com.abdullah.weatherapp.domain.di.NetworkComponent
import com.abdullah.weatherapp.domain.di.NetworkModule
import com.abdullah.weatherapp.domain.enum.ServiceConstants
import com.abdullah.weatherapp.util.StringHelper

/**
 * Created by abdullah on 27.1.2018.
 */

class WeatherApp : Application() {

    private var networkComponent: NetworkComponent? = null

    override fun onCreate() {
        super.onCreate()
        initDagger()
    }

    fun initDagger() {
        networkComponent = DaggerNetworkComponent.builder()
                .networkModule(NetworkModule(ServiceConstants.HTTP_URL.text,
                        StringHelper(applicationContext).getStringById(R.string.api_key)))
                .build()
    }

    fun getNetworkComponent(): NetworkComponent? {
        return networkComponent
    }
}
