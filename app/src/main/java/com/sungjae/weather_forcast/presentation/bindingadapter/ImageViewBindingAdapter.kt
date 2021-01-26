package com.sungjae.weather_forcast.presentation.bindingadapter

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.sungjae.weather_forcast.presentation.ext.loadUrl

@BindingAdapter("loadUrl", "placeHolder")
fun ImageView.loadUrl(url: String?, placeholder: Drawable) {
    loadUrl(url) {
        placeholder(placeholder)
    }
}
