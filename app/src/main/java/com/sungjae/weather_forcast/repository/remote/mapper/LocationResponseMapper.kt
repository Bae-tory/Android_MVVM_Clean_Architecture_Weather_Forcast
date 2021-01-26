package com.sungjae.weather_forcast.repository.remote.mapper

import com.sungjae.weather_forcast.repository.remote.model.location.LocationResponse
import com.sungjae.weather_forcast.repository.repository.model.LocationData

object LocationResponseMapper : RemoteMapper<LocationResponse, LocationData> {

    override fun LocationResponse.toRepository(): LocationData =
        LocationData(
            title,
            type,
            woeid,
            latLng
        )
}