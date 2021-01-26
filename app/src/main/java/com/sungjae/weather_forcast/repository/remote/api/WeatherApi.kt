package com.sungjae.weather_forcast.repository.remote.api

import com.sungjae.weather_forcast.di.NetWorkModule
import com.sungjae.weather_forcast.repository.remote.model.weather.WeatherResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path


/**
 * create instance in Rerofit Create
 * @see NetWorkModule.provideWeatherApiService
 */
interface WeatherApi {
    @GET("location/{woeid}")
    fun getLocationIncludedText(
        @Path("woeid")
        onEarthId: Int
    ): Single<WeatherResponse>
}