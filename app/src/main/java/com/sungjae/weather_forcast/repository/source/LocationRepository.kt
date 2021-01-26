package com.sungjae.weather_forcast.repository.source

import com.sungjae.weather_forcast.domain.entity.LocationEntity
import io.reactivex.rxjava3.core.Single

interface LocationRepository {

    fun getLocationList(locationAbbr: String): Single<List<LocationEntity>>

}