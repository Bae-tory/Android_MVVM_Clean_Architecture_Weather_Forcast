package com.sungjae.weather_forcast.repository.remote.model.location

import com.sungjae.weather_forcast.repository.remote.model.Response
import com.google.gson.annotations.SerializedName

data class LocationResponse(

    @SerializedName("title")
    val title: String,

    @SerializedName("location_type")
    val type: String,

    @SerializedName("woeid")
    val woeid: Int,/*	Where On Earth ID */

    @SerializedName("latt_long")
    val latLng: String

) : Response
