package com.sungjae.weather_forcast.domain

import com.sungjae.weather_forcast.di.UseCaseModule
import com.sungjae.weather_forcast.domain.entity.LocationEntity
import com.sungjae.weather_forcast.repository.source.LocationRepository
import io.reactivex.rxjava3.core.Single

/**
 * @see UseCaseModule
 */
class GetLocationListUseCase(private val locationRepository: LocationRepository) : BaseSingleUseCase<List<LocationEntity>, String>() {

    override fun buildUseCaseInputSingle(params: String): Single<List<LocationEntity>>? =
        locationRepository.getLocationList(params)

}