package com.sungjae.weather_forcast.repository.remote.model.weather

import com.sungjae.weather_forcast.repository.remote.model.Response
import com.google.gson.annotations.SerializedName

data class WeatherResponse(

    @SerializedName("consolidated_weather")
    val consolidatedWeather: List<ConsolidatedWeather>,

    @SerializedName("latt_long")
    val latLng: String,

    @SerializedName("location_type")
    val locationType: String,

    @SerializedName("parent")
    val parent: Parent,

    @SerializedName("sources")
    val sources: List<Source>,

    @SerializedName("sun_rise")
    val sunRise: String,

    @SerializedName("sun_set")
    val sunSet: String,

    @SerializedName("time")
    val time: String,

    @SerializedName("timezone")
    val timeZone: String,

    @SerializedName("timezone_name")
    val timeZoneName: String,

    @SerializedName("title")
    val title: String,

    @SerializedName("woeid")
    val woeid: Int

) : Response