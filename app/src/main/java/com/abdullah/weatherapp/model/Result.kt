package com.abdullah.weatherapp.model

/**
 * Created by abdullah on 24.1.2018.
 */
data class Weather(private val id: Int, private val main: String, private val description: String, private val icon: String)

data class Main(private val id: Int, private val main: String, private val description: String, private val icon: String)
data class Result(private val name: String, private val main: Main, private val weather: List<Weather>);