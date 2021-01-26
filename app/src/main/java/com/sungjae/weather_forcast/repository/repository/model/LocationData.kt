package com.sungjae.weather_forcast.repository.repository.model

data class LocationData(
    val title: String,
    val type: String,
    val woeid: Int,/*	Where On Earth ID */
    val latLng: String
) : Repository