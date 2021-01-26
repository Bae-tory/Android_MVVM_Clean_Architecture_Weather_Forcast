package com.sungjae.weather_forcast.domain.base

abstract class BaseUseCase<in Params> {

    abstract fun execute(params: Params): Any?

    abstract fun invoke(): Any?

}