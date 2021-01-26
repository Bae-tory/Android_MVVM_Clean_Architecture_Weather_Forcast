package com.sungjae.weather_forcast.di

import android.app.Application
import com.orhanobut.logger.*
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application() {
    override fun onCreate() {
        setAndroidLogger()

        super.onCreate()
    }

    private fun setAndroidLogger() {
        val formatStrategy: FormatStrategy = PrettyFormatStrategy.newBuilder()
            .methodCount(PRINT_STACK_COUNT)
            .build()

        Logger.addLogAdapter(object : AndroidLogAdapter(formatStrategy) {
            override fun isLoggable(priority: Int, tag: String?) = BuildConfig.DEBUG
        })
    }

    companion object {
        private const val PRINT_STACK_COUNT: Int = 10
    }
}