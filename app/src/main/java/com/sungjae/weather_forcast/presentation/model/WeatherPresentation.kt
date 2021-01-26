package com.sungjae.weather_forcast.presentation.model

import com.sungjae.weather_forcast.presentation.mapper.Presentation

data class WeatherPresentation(
    val locationTitle: String,
    val todayWeatherPresentation: TodayWeatherPresentation,
    val tomorrowWeatherPresentation: TomorrowWeatherPresentation
) : Presentation