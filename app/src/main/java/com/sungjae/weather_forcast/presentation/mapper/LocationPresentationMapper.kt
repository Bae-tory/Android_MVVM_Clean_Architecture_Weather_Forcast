package com.sungjae.weather_forcast.presentation.mapper

import com.sungjae.weather_forcast.domain.entity.LocationEntity
import com.sungjae.weather_forcast.presentation.model.LocationPresentation

object LocationPresentationMapper : PresentationMapper<LocationPresentation, LocationEntity> {

    override fun fromPresentation(presentation: LocationPresentation): LocationEntity =
        LocationEntity(
            presentation.title,
            presentation.type,
            presentation.woeid,
            presentation.latLng
        )


    override fun toPresentation(entity: LocationEntity): LocationPresentation =
        LocationPresentation(
            entity.title,
            entity.type,
            entity.woeid,
            entity.latLng
        )
}