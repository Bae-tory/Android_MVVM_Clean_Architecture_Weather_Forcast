package com.sungjae.weather_forcast.domain

import com.sungjae.weather_forcast.domain.base.BaseUseCase
import com.sungjae.weather_forcast.domain.exception.InvalidSingleException
import io.reactivex.rxjava3.core.Single

abstract class BaseSingleUseCase<T, in Params> : BaseUseCase<Params>() {

    protected abstract fun buildUseCaseInputSingle(params: Params): Single<T>?

    override fun execute(params: Params): Single<T> =
        buildUseCaseInputSingle(params = params)
            ?: Single.error(InvalidSingleException("BaseSingleUseCase Single is Invalid"))


    override fun invoke(): Nothing? = null
}