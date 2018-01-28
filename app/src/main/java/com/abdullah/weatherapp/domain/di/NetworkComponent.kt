package com.abdullah.weatherapp.domain.di

import com.abdullah.weatherapp.domain.interactor.CurrentWeatherInteractor
import dagger.Component
import javax.inject.Singleton

/**
 * Created by abdullah on 28.1.2018.
 */

@Singleton
@Component(modules = arrayOf(NetworkModule::class))
interface NetworkComponent {
    fun currentWeatherInteractor(): CurrentWeatherInteractor
}