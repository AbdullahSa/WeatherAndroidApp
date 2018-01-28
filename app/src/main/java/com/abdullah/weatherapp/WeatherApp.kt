package com.abdullah.weatherapp

import android.app.Application
import com.abdullah.weatherapp.domain.di.DaggerNetworkComponent
import com.abdullah.weatherapp.domain.di.NetworkComponent
import com.abdullah.weatherapp.domain.di.NetworkModule

/**
 * Created by abdullah on 27.1.2018.
 */

class WeatherApp : Application() {

    private var networkComponent: NetworkComponent? = null

    private var instance: WeatherApp? = null

    public fun getInstance(): WeatherApp? {
        if (instance == null) {
            instance = WeatherApp()
        }
        return instance
    }

    override fun onCreate() {
        super.onCreate()
        initDagger()
    }

    fun initDagger() {
        networkComponent = DaggerNetworkComponent.builder()
                .networkModule(NetworkModule("api.openweathermap.org/data/2.5/",
                        "954eb59461f972e120a38082d72f97a5"))
                .build()
    }

    fun getNetworkComponent(): NetworkComponent? {
        return networkComponent
    }
}
