package com.abdullah.weatherapp.view

import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.Unbinder
import com.abdullah.weatherapp.R
import com.abdullah.weatherapp.WeatherApp
import com.abdullah.weatherapp.domain.enum.ServiceConstants
import com.abdullah.weatherapp.model.Result
import com.abdullah.weatherapp.presenter.MainPresenter
import com.abdullah.weatherapp.util.StringHelper
import javax.inject.Inject

class MainActivity : AppCompatActivity(), MainView {

    private var unbinder: Unbinder? = null

    @BindView(R.id.text_degree)
    lateinit var textViewDegree: TextView

    @BindView(R.id.text_description)
    lateinit var textViewDescription: TextView

    @BindView(R.id.image_weather_icon)
    lateinit var imageViewWeatherIcon: ImageView

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
        val weather = result.weather.get(0);
        textViewDegree.setText(result.main.temp + " " + StringHelper(baseContext).getStringById(R.string.celcius))
        textViewDescription.setText(weather.description)
        imageViewWeatherIcon.setImageURI(Uri.parse(ServiceConstants.HTTP_IMAGE_URL.text
                + weather.icon + StringHelper(baseContext).getStringById(R.string.png)))
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
        unbinder?.unbind()
        unbinder = null
    }
}
