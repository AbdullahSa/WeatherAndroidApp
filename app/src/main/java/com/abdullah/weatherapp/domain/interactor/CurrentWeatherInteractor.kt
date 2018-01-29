package com.abdullah.weatherapp.domain.interactor

import com.abdullah.weatherapp.domain.service.rest.CurrentWeatherService
import com.abdullah.weatherapp.model.Result
import com.abdullah.weatherapp.model.entity.Location
import rx.Single
import javax.inject.Inject

/**
 * Created by abdullah on 27.1.2018.
 */
class CurrentWeatherInteractor @Inject constructor(val currentWeatherService: CurrentWeatherService) {

    public fun getCurrentWeatherServiceByLocation(location: Location): Single<Result> {
        return currentWeatherService.getCurrentValueByLocation(location.lat, location.lon)
    }
}