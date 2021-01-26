package com.sungjae.weather_forcast.domain.entity

import com.sungjae.weather_forcast.domain.Entity

data class WeatherEntity(
    val weatherStateName: String,
    val weatherStateAbbr: String,
    val theTemp: Double,
    val humidity: Int
) : Entity