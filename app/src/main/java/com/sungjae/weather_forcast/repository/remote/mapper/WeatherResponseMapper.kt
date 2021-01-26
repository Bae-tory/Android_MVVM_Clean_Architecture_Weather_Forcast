package com.sungjae.weather_forcast.repository.remote.mapper

import com.sungjae.weather_forcast.repository.remote.model.weather.ConsolidatedWeather
import com.sungjae.weather_forcast.repository.repository.model.WeatherData

object WeatherResponseMapper : RemoteMapper<List<ConsolidatedWeather>, List<WeatherData>> {

    override fun List<ConsolidatedWeather>.toRepository(): List<WeatherData> =
        map {
            WeatherData(
                weatherStateName = it.weatherStateName,
                weatherStateAbbr = it.weatherStateAbbr,
                theTemp = it.theTemp,
                humidity = it.humidity
            )
        }
}