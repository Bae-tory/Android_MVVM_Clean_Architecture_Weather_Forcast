package com.sungjae.weather_forcast.presentation.model

import com.sungjae.weather_forcast.presentation.mapper.Presentation

data class TodayWeatherPresentation(
    val weatherStateAbbr: String,
    val weatherStateName: String,
    val theTemp: Float,
    val humidity: Int
) : Presentation