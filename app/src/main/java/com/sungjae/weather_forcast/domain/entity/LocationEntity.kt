package com.sungjae.weather_forcast.domain.entity

import com.sungjae.weather_forcast.domain.Entity

data class LocationEntity(
    val title: String,
    val type: String,
    val woeid: Int,/*	Where On Earth ID */
    val latLng: String
) : Entity
