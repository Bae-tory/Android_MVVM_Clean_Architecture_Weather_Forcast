package com.sungjae.weather_forcast.repository.repository.mapper

import com.sungjae.weather_forcast.domain.Entity
import com.sungjae.weather_forcast.repository.repository.model.Repository

interface RepositoryMapper<I : Repository, O : Entity> {

    fun I.toEntity(): O

    fun O.fromEntity(): I

}
