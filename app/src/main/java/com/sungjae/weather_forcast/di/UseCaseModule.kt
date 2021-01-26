package com.sungjae.weather_forcast.di

import com.sungjae.weather_forcast.domain.GetLocationListUseCase
import com.sungjae.weather_forcast.domain.GetWeatherListUseCase
import com.sungjae.weather_forcast.repository.source.LocationRepository
import com.sungjae.weather_forcast.repository.source.WeatherRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

/**
 * for Domain layer
 */

@Module
@InstallIn(ApplicationComponent::class)
object UseCaseModule {

    @Provides
    fun provideGetLocationUseCase(repository: LocationRepository): GetLocationListUseCase =
        GetLocationListUseCase(repository)

    @Provides
    fun provideGetWeatherUseCase(repository: WeatherRepository): GetWeatherListUseCase =
        GetWeatherListUseCase(repository)

}