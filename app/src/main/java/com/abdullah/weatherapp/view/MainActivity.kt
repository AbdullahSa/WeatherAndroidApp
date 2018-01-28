package com.abdullah.weatherapp.view

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.abdullah.weatherapp.R
import com.abdullah.weatherapp.WeatherApp
import com.abdullah.weatherapp.model.Result
import com.abdullah.weatherapp.presenter.MainPresenter
import javax.inject.Inject

class MainActivity : AppCompatActivity(), MainView {

    @Inject
    lateinit var presenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initDagger()
        presenter.attachView(this)
        //TODO will be called LocationManager
    }

    private fun initDagger() {
        DaggerMainActivityComponent.builder().networkComponent(WeatherApp().getNetworkComponent())
                .build().inject(this)
    }

    override fun fillWeatherResult(result: Result) {
        //TODO will be implemented to UI
    }
}
