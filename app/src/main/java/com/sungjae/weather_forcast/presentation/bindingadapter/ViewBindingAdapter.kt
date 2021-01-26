package com.sungjae.weather_forcast.presentation.bindingadapter

import android.view.View
import androidx.databinding.BindingAdapter
import com.sungjae.weather_forcast.presentation.ext.ThrottleFirstClickListener

@BindingAdapter("onThrottleFirstClick", "throttleDelayTime", requireAll = false)
fun View.setOnThrottleClickListener(listener: View.OnClickListener, delayTime: Int) {
    setOnClickListener(ThrottleFirstClickListener({
        it.run(listener::onClick)
    }, delayTime.toLong()))
}