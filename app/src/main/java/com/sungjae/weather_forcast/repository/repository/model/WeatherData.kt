package com.sungjae.weather_forcast.repository.repository.model

data class WeatherData(
    val weatherStateName: String,
    val weatherStateAbbr: String,
    val theTemp: Double,
    val humidity: Int
) : Repository