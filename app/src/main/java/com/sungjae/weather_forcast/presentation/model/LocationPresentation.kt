package com.sungjae.weather_forcast.presentation.model

import com.sungjae.weather_forcast.presentation.mapper.Presentation

data class LocationPresentation(
    val title: String,
    val type: String = "",
    val woeid: Int,/*	Where On Earth ID */
    val latLng: String = ""
) : Presentation