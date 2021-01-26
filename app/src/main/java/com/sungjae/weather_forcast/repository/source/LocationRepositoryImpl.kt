package com.sungjae.weather_forcast.repository.source

import com.sungjae.weather_forcast.domain.entity.LocationEntity
import com.sungjae.weather_forcast.repository.remote.source.LocationRemoteDataSource
import com.sungjae.weather_forcast.repository.repository.mapper.LocationDataMapper.toEntity
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class LocationRepositoryImpl @Inject constructor(
    private val locationRemoteDataSource: LocationRemoteDataSource
) : LocationRepository {

    override fun getLocationList(locationAbbr: String): Single<List<LocationEntity>> =
        locationRemoteDataSource
            .getLocationList(locationAbbr)
            .flatMap { list ->
                Single.just(list.map { it.toEntity() })
            }


}