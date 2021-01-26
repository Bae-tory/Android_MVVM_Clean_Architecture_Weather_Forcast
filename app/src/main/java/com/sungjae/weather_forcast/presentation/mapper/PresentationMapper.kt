package com.sungjae.weather_forcast.presentation.mapper

import com.sungjae.weather_forcast.domain.Entity

interface PresentationMapper<I : Presentation, O : Entity> {

    fun fromPresentation(presentation: I): O

    fun toPresentation(entity : O): I

}
