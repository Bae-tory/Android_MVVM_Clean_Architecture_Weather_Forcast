package com.sungjae.weather_forcast.presentation.component.state

import android.os.Bundle
import androidx.core.os.bundleOf

sealed class UiState {

    object InitialLoading : UiState()

    object Loading : UiState()

    data class Success(
        val successCode: Int,
        val successMsg: String,

        /**
         * @see bundleOf : data transfer
         */
        val data: Bundle
    ) : UiState()

    data class Error(
        val throwable: Throwable,
        val errorMsgRes: Int
    ) : UiState()
}