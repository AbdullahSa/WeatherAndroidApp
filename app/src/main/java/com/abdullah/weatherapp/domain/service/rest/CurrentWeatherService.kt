package com.abdullah.weatherapp.domain.service.rest

import com.abdullah.weatherapp.model.Result
import retrofit2.http.GET
import retrofit2.http.Query
import rx.Single

/**
 * Created by abdullah on 24.1.2018.
 */
interface CurrentWeatherService {
    @GET("weather")
    fun getCurrentValueByLocation(@Query("lat") lat: Float?, @Query("lon") lon: Float?): Single<Result>
}