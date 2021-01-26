package com.sungjae.weather_forcast.di

import com.sungjae.weather_forcast.repository.remote.source.LocationRemoteDataSource
import com.sungjae.weather_forcast.repository.remote.source.LocationRemoteDataSourceImpl
import com.sungjae.weather_forcast.repository.remote.source.WeatherRemoteDataSource
import com.sungjae.weather_forcast.repository.remote.source.WeatherRemoteDataSourceImpl
import com.sungjae.weather_forcast.repository.remote.api.LocationApi
import com.sungjae.weather_forcast.repository.remote.api.WeatherApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

/**
 * for Remote Layer
 */
@Module
@InstallIn(ApplicationComponent::class)
object RemoteDataSourceModule {

    @Provides
    @Singleton
    fun provideLocationRemoteDataSoruce(api: LocationApi): LocationRemoteDataSource =
        LocationRemoteDataSourceImpl(api)

    @Provides
    @Singleton
    fun provideWeatherRemoteDataSoruce(api: WeatherApi): WeatherRemoteDataSource =
        WeatherRemoteDataSourceImpl(api)

}
