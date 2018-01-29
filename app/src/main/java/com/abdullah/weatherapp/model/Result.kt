package com.abdullah.weatherapp.model

/**
 * Created by abdullah on 24.1.2018.
 */
data class Weather(val id: Int, val main: String, val description: String, val icon: String)

data class Main(val temp: String)
data class Result(val name: String, val main: Main, val weather: List<Weather>);