package com.sungjae.weather_forcast.repository.repository.mapper

import com.sungjae.weather_forcast.domain.entity.WeatherEntity
import com.sungjae.weather_forcast.repository.repository.model.WeatherData

object WeatherDataMapper : RepositoryMapper<WeatherData, WeatherEntity> {

    override fun WeatherData.toEntity(): WeatherEntity =
        WeatherEntity(weatherStateName, weatherStateAbbr, theTemp, humidity)


    override fun WeatherEntity.fromEntity(): WeatherData =
        WeatherData(weatherStateName, weatherStateAbbr, theTemp, humidity)

}