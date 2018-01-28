package com.abdullah.weatherapp.presenter

import com.abdullah.weatherapp.model.entity.Location
import com.abdullah.weatherapp.view.MainView

/**
 * Created by abdullah on 24.1.2018.
 */
interface MainPresenter {

    fun attachView(view: MainView)
    fun detachView()

    fun getLastWeatherStateByLocation(location: Location)
}