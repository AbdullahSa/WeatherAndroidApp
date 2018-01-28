package com.abdullah.weatherapp.view

import com.abdullah.weatherapp.domain.di.NetworkComponent
import dagger.Component
import javax.inject.Singleton

/**
 * Created by abdullah on 28.1.2018.
 */
@ActivityScope
@Component(dependencies = arrayOf(NetworkComponent::class), modules = arrayOf(MainActivityModule::class))
interface MainActivityComponent {
    fun inject(mainActivity: MainActivity)
}