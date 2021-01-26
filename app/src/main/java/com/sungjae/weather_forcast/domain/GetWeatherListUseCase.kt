package com.sungjae.weather_forcast.domain

import com.sungjae.weather_forcast.di.UseCaseModule
import com.sungjae.weather_forcast.domain.entity.WeatherEntity
import com.sungjae.weather_forcast.repository.source.WeatherRepository
import io.reactivex.rxjava3.core.Single

/**
 * @see UseCaseModule
 */
class GetWeatherListUseCase(private val weatherRepository: WeatherRepository) :
    BaseSingleUseCase<List<WeatherEntity>, Int>() {

    override fun buildUseCaseInputSingle(params: Int): Single<List<WeatherEntity>> =
        weatherRepository.getWeatherList(params)

}