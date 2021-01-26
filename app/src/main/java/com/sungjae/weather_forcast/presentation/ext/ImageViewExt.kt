package com.sungjae.weather_forcast.presentation.ext

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

fun ImageView.loadUrl(url: String?, block: RequestOptions.() -> RequestOptions) {
    val option = RequestOptions()
    option.block()

    Glide.with(context)
        .load(url)
        .apply(option)
        .into(this)
}