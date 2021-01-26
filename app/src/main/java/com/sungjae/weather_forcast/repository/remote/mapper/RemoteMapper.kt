package com.sungjae.weather_forcast.repository.remote.mapper

interface RemoteMapper<in I, out O> {

    fun I.toRepository(): O

}