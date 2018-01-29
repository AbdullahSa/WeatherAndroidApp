package com.abdullah.weatherapp.view

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.Unbinder
import com.abdullah.weatherapp.R
import com.abdullah.weatherapp.WeatherApp
import com.abdullah.weatherapp.model.Result
import com.abdullah.weatherapp.presenter.MainPresenter
import javax.inject.Inject

class MainActivity : AppCompatActivity(), MainView {

    private var unbinder: Unbinder? = null

    @BindView(R.id.text_degree)
    lateinit var textViewDegree: TextView

    @Inject
    lateinit var presenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        unbinder = ButterKnife.bind(this)
        initDagger()
        presenter.attachView(this, this)
    }

    private fun initDagger() {
        DaggerMainActivityComponent.builder().networkComponent((application as WeatherApp).getNetworkComponent())
                .build().inject(this)
    }

    override fun fillWeatherResult(result: Result) {
        textViewDegree.setText(result.main.temp + "degree " + result.weather.get(0).description)

    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
        unbinder?.unbind()
        unbinder = null
    }
}
