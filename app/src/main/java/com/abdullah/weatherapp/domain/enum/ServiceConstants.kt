package com.abdullah.weatherapp.domain.enum

import android.support.annotation.StringDef

/**
 * Created by Abdullah on 2.2.2018.
 */
enum class ServiceConstants(val text: String) {
    HTTP_URL("http://api.openweathermap.org/data/2.5/"),
    HTTP_IMAGE_URL("http://openweathermap.org/img/w/"),
    DEGREE_PARAMETER("metric"),
    APP_ID("appid"),
    UNITS("units")


}