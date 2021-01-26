package com.sungjae.weather_forcast.repository.repository.mapper

import com.sungjae.weather_forcast.domain.entity.LocationEntity
import com.sungjae.weather_forcast.repository.repository.model.LocationData

object LocationDataMapper : RepositoryMapper<LocationData, LocationEntity> {

    override fun LocationData.toEntity(): LocationEntity =
        LocationEntity(title, type, woeid, latLng)


    override fun LocationEntity.fromEntity(): LocationData =
        LocationData(title, type, woeid, latLng)

}