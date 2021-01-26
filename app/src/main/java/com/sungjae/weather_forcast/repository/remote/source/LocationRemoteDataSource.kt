package com.sungjae.weather_forcast.repository.remote.source

import com.sungjae.weather_forcast.repository.repository.model.LocationData
import io.reactivex.rxjava3.core.Single

interface LocationRemoteDataSource {

    fun getLocationList(locationAbbr: String): Single<List<LocationData>>

}