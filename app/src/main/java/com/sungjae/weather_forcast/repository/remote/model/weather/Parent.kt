package com.sungjae.weather_forcast.repository.remote.model.weather

import com.sungjae.weather_forcast.repository.remote.model.Response
import com.google.gson.annotations.SerializedName

data class Parent(

    @SerializedName("latt_long")
    val latLng: String,

    @SerializedName("location_type")
    val locationType: String,

    @SerializedName("title")
    val title: String,

    @SerializedName("woeid")
    val woeid: Int

) : Response