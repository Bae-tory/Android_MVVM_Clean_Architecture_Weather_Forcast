package com.sungjae.weather_forcast.presentation.ui

import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.ProgressBar
import androidx.activity.viewModels
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.sungjae.weather_forcast.R
import com.sungjae.weather_forcast.databinding.ActivityWeatherListBinding
import com.sungjae.weather_forcast.presentation.base.BaseActivity
import com.sungjae.weather_forcast.presentation.component.state.UiState
import com.sungjae.weather_forcast.presentation.ext.toast
import com.sungjae.weather_forcast.presentation.model.WeatherPresentation
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers

@AndroidEntryPoint
class WeatherListActivity : BaseActivity<ActivityWeatherListBinding, WeatherListViewModel>
    (R.layout.activity_weather_list) {

    override val vm: WeatherListViewModel by viewModels()

    private val loadingStub: View by lazy {
        findViewById<View>(R.id.loading_stub)
    }

    private val loadingProgressBar: ProgressBar by lazy {
        loadingStub.findViewById<ProgressBar>(R.id.progress_bar_loading)
    }

    private val recyclerView: RecyclerView by lazy {
        findViewById<RecyclerView>(R.id.rv_weather)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        observeWeatherData()
        observeWeatherItemClick()
    }

    private fun observeWeatherItemClick() {
        vm.rvClickEventLiveData.observe {
            toast((it as WeatherPresentation).toString())
        }
    }

    private fun observeWeatherData() {
        vm.uiStateSubject // 뷰에 데이터 반영 및 뷰 조작
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ uiState ->
                loadingViewControl(uiState)
                when (uiState) {
                    is UiState.InitialLoading -> {
                        toast(getString(R.string.loading_weather))
                        //TODO When InitialLoading
                    }
                    is UiState.Loading -> {
                        //TODO When Loading
                    }
                    is UiState.Success -> {
                        //TODO When Success
                    }
                    is UiState.Error -> {
                        //TODO When Error
                        toast(
                            getString(
                                R.string.error_msg,
                                uiState.throwable.message,
                                getString(uiState.errorMsgRes)
                            )
                        )
                    }
                }
            }, {
                it.printStackTrace()
            }).addDisposable()
    }

    override fun ActivityWeatherListBinding.doInitBinding() {

    }

    override fun ActivityWeatherListBinding.doOnCreateAfterInitBinding() {
    }

    override fun ActivityWeatherListBinding.doOnResume() {
    }

    override fun ActivityWeatherListBinding.doOnStart() {
    }

    override fun ActivityWeatherListBinding.doJustBeforeStop() {
    }

    override fun ActivityWeatherListBinding.doOnRestart() {
    }

    override fun ActivityWeatherListBinding.doJustBeforeDestroy() {
    }

    override fun ActivityWeatherListBinding.doOnBackPressed() {

    }

    private fun loadingViewControl(uiState: UiState) { // 로딩 뷰 컨트롤
        bind {
            when (uiState) {
                is UiState.InitialLoading -> {
                    loadingStub.isVisible = true
                    loadingProgressBar.isVisible = true
                }
                is UiState.Loading -> {
                    recyclerView.isInvisible = true
                }
                is UiState.Success -> {
                    loadingStub.isVisible = false
                    recyclerView.isInvisible = false
                }
                is UiState.Error -> {
                    loadingStub.isVisible = true
                    recyclerView.isInvisible = true
                }
            }
        }

        when (uiState) {
            is UiState.InitialLoading, UiState.Loading -> {
                window.setFlags(
                    WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                    WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
                )
            }
            else -> {
                window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
            }
        }
    }
}