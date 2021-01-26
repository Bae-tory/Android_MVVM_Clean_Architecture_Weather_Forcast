package com.sungjae.weather_forcast.repository.remote.source

import com.sungjae.weather_forcast.di.RemoteDataSourceModule
import com.sungjae.weather_forcast.repository.remote.api.WeatherApi
import com.sungjae.weather_forcast.repository.remote.mapper.WeatherResponseMapper.toRepository
import com.sungjae.weather_forcast.repository.repository.model.WeatherData
import io.reactivex.rxjava3.core.Single

/**
 * @see RemoteDataSourceModule
 * */
internal class WeatherRemoteDataSourceImpl(
    private val weatherApi: WeatherApi
) : WeatherRemoteDataSource {

    override fun getWeatherList(onEarthId: Int): Single<List<WeatherData>> =
        weatherApi
            .getLocationIncludedText(onEarthId)
            .flatMap { Single.just(it.consolidatedWeather.toRepository()) }
}