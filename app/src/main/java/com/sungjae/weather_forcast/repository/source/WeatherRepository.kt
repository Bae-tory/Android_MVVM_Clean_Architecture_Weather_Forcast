package com.sungjae.weather_forcast.repository.source

import com.sungjae.weather_forcast.domain.entity.WeatherEntity
import io.reactivex.rxjava3.core.Single

interface WeatherRepository {

    fun getWeatherList(onEarthId: Int): Single<List<WeatherEntity>>

}