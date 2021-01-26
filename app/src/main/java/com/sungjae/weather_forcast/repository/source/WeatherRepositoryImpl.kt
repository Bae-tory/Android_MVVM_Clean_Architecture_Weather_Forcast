package com.sungjae.weather_forcast.repository.source

import com.sungjae.weather_forcast.domain.entity.WeatherEntity
import com.sungjae.weather_forcast.repository.remote.source.WeatherRemoteDataSource
import com.sungjae.weather_forcast.repository.repository.mapper.WeatherDataMapper.toEntity
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject


class WeatherRepositoryImpl @Inject constructor(
    private val weatherRemoteDataSource: WeatherRemoteDataSource
) : WeatherRepository {

    override fun getWeatherList(onEarthId: Int): Single<List<WeatherEntity>> =
        weatherRemoteDataSource
            .getWeatherList(onEarthId)
            .flatMap { list ->
                Single.just(list.map { it.toEntity() })
            }

}

