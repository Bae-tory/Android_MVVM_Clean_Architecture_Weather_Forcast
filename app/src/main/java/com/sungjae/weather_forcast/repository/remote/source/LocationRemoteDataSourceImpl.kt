package com.sungjae.weather_forcast.repository.remote.source

import com.sungjae.weather_forcast.di.RemoteDataSourceModule
import com.sungjae.weather_forcast.repository.remote.api.LocationApi
import com.sungjae.weather_forcast.repository.remote.mapper.LocationResponseMapper.toRepository
import com.sungjae.weather_forcast.repository.repository.model.LocationData
import io.reactivex.rxjava3.core.Single

/**
 * @see RemoteDataSourceModule
 * */
class LocationRemoteDataSourceImpl(private val locationApi: LocationApi) : LocationRemoteDataSource {

    override fun getLocationList(locationAbbr: String): Single<List<LocationData>> =
        locationApi
            .getLocationIncludedText(locationAbbr)
            .flatMap { list ->
                Single.just(list.map { it.toRepository() })
            }

}