package com.sungjae.weather_forcast.presentation.base

import androidx.lifecycle.ViewModel
import com.sungjae.weather_forcast.presentation.component.state.UiState
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.subjects.BehaviorSubject

abstract class BaseViewModel : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    internal val uiStateSubject: BehaviorSubject<UiState> = BehaviorSubject.create()

    protected fun Disposable.addDisposable() {
        compositeDisposable.add(this)
    }

    override fun onCleared() {
        doJustBeforeCleared()
        super.onCleared()
    }

    /**
     * Do Resource Release
     */
    open fun doJustBeforeCleared() {
        compositeDisposable.clear()
    }
}