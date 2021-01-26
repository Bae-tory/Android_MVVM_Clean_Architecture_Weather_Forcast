package com.sungjae.weather_forcast.di

import com.sungjae.weather_forcast.repository.remote.source.LocationRemoteDataSource
import com.sungjae.weather_forcast.repository.remote.source.WeatherRemoteDataSource
import com.sungjae.weather_forcast.repository.source.LocationRepository
import com.sungjae.weather_forcast.repository.source.LocationRepositoryImpl
import com.sungjae.weather_forcast.repository.source.WeatherRepository
import com.sungjae.weather_forcast.repository.source.WeatherRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideLocationRepository(locationRemoteDataSource: LocationRemoteDataSource): LocationRepository =
        LocationRepositoryImpl(locationRemoteDataSource)

    @Provides
    @Singleton
    fun provideWeatherRepository(weatherRemoteDataSource: WeatherRemoteDataSource): WeatherRepository =
        WeatherRepositoryImpl(weatherRemoteDataSource)

}