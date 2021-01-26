package com.sungjae.weather_forcast.repository.remote.api

import com.sungjae.weather_forcast.di.NetWorkModule
import com.sungjae.weather_forcast.repository.remote.model.location.LocationResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query


/**
 * create instance in Rerofit Create
 * @see NetWorkModule.provideLocationRetrofit
 */
interface LocationApi {
    @GET("location/search")
    fun getLocationIncludedText(
        @Query("query") locationAbbr: String
    ): Single<List<LocationResponse>>
}