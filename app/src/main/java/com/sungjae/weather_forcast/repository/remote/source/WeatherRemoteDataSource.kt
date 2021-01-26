package com.sungjae.weather_forcast.repository.remote.source

import com.sungjae.weather_forcast.repository.repository.model.WeatherData
import io.reactivex.rxjava3.core.Single

interface WeatherRemoteDataSource {

    fun getWeatherList(onEarthId: Int): Single<List<WeatherData>>

}