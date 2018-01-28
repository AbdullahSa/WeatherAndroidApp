package com.abdullah.weatherapp.domain.interactor

import com.abdullah.weatherapp.domain.service.CurrentWeatherService
import com.abdullah.weatherapp.model.Result
import com.abdullah.weatherapp.model.entity.Location
import rx.Single
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by abdullah on 27.1.2018.
 */
class CurrentWeatherInteractor @Inject constructor(val currentWeatherService: CurrentWeatherService) {

    public fun getCurrentWeatherServiceByLocation(location: Location): Single<Result> {
        return currentWeatherService.getCurrentValueByLocation(location.lat, location.lon)
    }
}