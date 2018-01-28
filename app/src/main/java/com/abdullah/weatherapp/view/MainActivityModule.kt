package com.abdullah.weatherapp.view

import com.abdullah.weatherapp.domain.interactor.CurrentWeatherInteractor
import com.abdullah.weatherapp.presenter.MainPresenter
import com.abdullah.weatherapp.presenter.impl.MainPresenterImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by abdullah on 28.1.2018.
 */
@Module
class MainActivityModule {

    @Provides
    @ActivityScope
    fun providePresenter(currentWeatherInteractor: CurrentWeatherInteractor): MainPresenter {
        return MainPresenterImpl(currentWeatherInteractor)
    }

}