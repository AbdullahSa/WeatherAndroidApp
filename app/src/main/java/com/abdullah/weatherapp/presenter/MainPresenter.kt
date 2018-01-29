package com.abdullah.weatherapp.presenter

import android.content.Context
import com.abdullah.weatherapp.model.entity.Location
import com.abdullah.weatherapp.view.MainView

/**
 * Created by abdullah on 24.1.2018.
 */
interface MainPresenter {

    fun attachView(view: MainView, context: Context)
    fun detachView()

    fun getLastWeatherStateByLocation(location: Location)
}