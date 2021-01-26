package com.sungjae.weather_forcast.presentation.ui

import androidx.core.os.bundleOf
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.sungjae.weather_forcast.R
import com.sungjae.weather_forcast.domain.GetLocationListUseCase
import com.sungjae.weather_forcast.domain.GetWeatherListUseCase
import com.sungjae.weather_forcast.domain.entity.LocationEntity
import com.sungjae.weather_forcast.domain.entity.WeatherEntity
import com.sungjae.weather_forcast.presentation.base.BaseViewModel
import com.sungjae.weather_forcast.presentation.component.state.UiState
import com.sungjae.weather_forcast.presentation.listener.RvItemClickListener
import com.sungjae.weather_forcast.presentation.mapper.LocationPresentationMapper
import com.sungjae.weather_forcast.presentation.model.LocationPresentation
import com.sungjae.weather_forcast.presentation.model.TodayWeatherPresentation
import com.sungjae.weather_forcast.presentation.model.TomorrowWeatherPresentation
import com.sungjae.weather_forcast.presentation.model.WeatherPresentation
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class WeatherListViewModel @ViewModelInject constructor(
    private val getLocationListUseCase: GetLocationListUseCase,
    private val getWeatherListUseCase: GetWeatherListUseCase
) : BaseViewModel(), RvItemClickListener {

    private val _weatherLiveData = MutableLiveData<List<WeatherPresentation>>()
    val weatherLiveData: LiveData<List<WeatherPresentation>> get() = _weatherLiveData

    private val _isRefreshLiveData = MutableLiveData<Boolean>(false)
    val isRefreshLiveData: LiveData<Boolean> get() = _isRefreshLiveData

    private val _rvClickEventLiveData = MutableLiveData<Any>()
    val rvClickEventLiveData: LiveData<Any> get() = _rvClickEventLiveData

    init {
        uiStateSubject.onNext(UiState.InitialLoading)
        showWeatherInfo()
    }

    fun showWeatherInfo() {
        _isRefreshLiveData.value = true
        if (uiStateSubject.value != UiState.InitialLoading) {
            uiStateSubject.onNext(UiState.Loading)
        }

        val searchText = "se"

        getLocationListUseCase
            .execute(searchText)
            .onErrorReturn {
                it.printStackTrace()
                uiStateSubject
                    .onNext(
                        UiState.Error(it, errorMsgRes = R.string.fail_get_location_data)
                    )
                listOf<LocationEntity>()
            }
            .subscribeOn(Schedulers.io())
            .map { list ->
                list.asSequence()
                    .map { LocationPresentationMapper.toPresentation(it) }
                    .map { LocationPresentation(woeid = it.woeid, title = it.title) }
                    .toList()
            }
            .subscribe({ locationList ->

                Single.just(locationList.map { it.woeid })
                    .toObservable()
                    .flatMap {
                        Observable.fromIterable(it)
                    }
                    .concatMapEager {
                        getWeatherListUseCase.execute(it)
                            .subscribeOn(Schedulers.io())
                            .toObservable()
                    }
                    .toList()
                    .observeOn(AndroidSchedulers.mainThread())
                    .onErrorReturn {
                        it.printStackTrace()
                        uiStateSubject
                            .onNext(
                                UiState.Error(it, errorMsgRes = R.string.fail_get_weather_data)
                            )
                        listOf<List<WeatherEntity>>()
                    }
                    .doAfterSuccess {
                        _isRefreshLiveData.value = false
                        uiStateSubject.onNext(
                            UiState.Success(0, "", bundleOf())
                        )
                    }
                    .subscribe({ entityLists ->
                        _weatherLiveData.value =
                            entityLists.asSequence()
                                .mapIndexed { _, entityList -> // List<WeatherEntity>
                                    entityList.filterIndexed { i, _ -> i < 2 }
                                }
                                .mapIndexed { i, filteredEntityList ->

                                    WeatherPresentation(
                                        locationTitle = locationList[i].title,
                                        todayWeatherPresentation = TodayWeatherPresentation(
                                            weatherStateAbbr = filteredEntityList[0].weatherStateAbbr,
                                            weatherStateName = filteredEntityList[0].weatherStateName,
                                            theTemp = filteredEntityList[0].theTemp.toFloat(),
                                            humidity = filteredEntityList[0].humidity
                                        ),
                                        tomorrowWeatherPresentation = TomorrowWeatherPresentation(
                                            weatherStateAbbr = filteredEntityList[1].weatherStateAbbr,
                                            weatherStateName = filteredEntityList[1].weatherStateName,
                                            theTemp = filteredEntityList[1].theTemp.toFloat(),
                                            humidity = filteredEntityList[1].humidity
                                        )
                                    )
                                }.toList()

                    }, // end of Single.just(onEarthIdList)
                        {
                            it.printStackTrace()
                        })
            },// end of getLocationListUseCase subscribe
                {
                    it.printStackTrace()
                }).addDisposable()

    }

    /**
     * Do Resource Release
     * BaseViewModel release disposables automatically
     * It is not required to be implemented just for disposables bag clear
     * @see BaseViewModel.doJustBeforeCleared
     */
    override fun doJustBeforeCleared() {
        super.doJustBeforeCleared()
    }

    override fun rvOnItemClick(item: Any) {
        _rvClickEventLiveData.value = item
    }

    companion object {
        const val LOCATION_LIST = "WeatherList"
        const val WEATHER_LIST = "WeatherList"
    }
}