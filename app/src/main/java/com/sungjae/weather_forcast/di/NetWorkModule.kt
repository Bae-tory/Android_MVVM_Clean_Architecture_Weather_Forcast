package com.sungjae.weather_forcast.di

import com.sungjae.weather_forcast.BuildConfig
import com.sungjae.weather_forcast.repository.remote.api.LocationApi
import com.sungjae.weather_forcast.repository.remote.api.WeatherApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import io.reactivex.rxjava3.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton


/**
 * for Remote Layer
 */

@Module
@InstallIn(ApplicationComponent::class)
object NetWorkModule {

    @Provides
    @Singleton
    fun provideOtherOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = if (BuildConfig.DEBUG) {
                    HttpLoggingInterceptor.Level.BODY
                } else {
                    HttpLoggingInterceptor.Level.NONE
                }
            })
            .build()
    }

    @Provides
    @Singleton
    @Named("location")
    fun provideLocationRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL_LOCATION)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .client(provideOtherOkHttpClient())
            .build()
    }

    @Provides
    @Singleton
    @Named("weather")
    fun provideWeatherRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL_WEATHER)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .client(provideOtherOkHttpClient())
            .build()
    }

    @Provides
    @Singleton
    fun provideLocationApi(@Named("location") retrofit: Retrofit): LocationApi {
        return retrofit.create(LocationApi::class.java)
    }

    @Provides
    @Singleton
    fun provideWeatherApi(@Named("weather") retrofit: Retrofit): WeatherApi {
        return retrofit.create(WeatherApi::class.java)
    }

//    @Provides
//    @Singleton
//    fun provideLoggingInterceptor(): HttpLoggingInterceptor =
//        HttpLoggingInterceptor().apply {
//            level = if (BuildConfig.DEBUG) {
//                HttpLoggingInterceptor.Level.BODY
//            } else {
//                HttpLoggingInterceptor.Level.NONE
//            }
//        }
//
//    @Provides
//    @Singleton
//    fun provideHeaderInterceptor(): Interceptor =
//        Interceptor { chain ->
//            val request =
//                chain.request()
//                    .newBuilder()
//                    .build()
//
//            chain.proceed(request)
//        }
//
//    @Provides
//    @Singleton
//    @Named("generalClient")
//    fun provideOkHttpClient(
//        loggingInterceptor: HttpLoggingInterceptor,
//        headerInterceptor: Interceptor
//    ): OkHttpClient = OkHttpClient.Builder()
//        .readTimeout(READ_TIME_OUT, TimeUnit.SECONDS)
//        .writeTimeout(WRITE_TIME_OUT, TimeUnit.SECONDS)
//        .callTimeout(CALL_TIME_OUT, TimeUnit.SECONDS)
//        .retryOnConnectionFailure(true)
//        .connectTimeout(CONNECT_TIME_OUT, TimeUnit.SECONDS)
//        .addInterceptor(loggingInterceptor)
//        .addInterceptor(headerInterceptor)
//        .build()
//
//
//    @Provides
//    @Singleton
//    @Named("locationRetrofit")
//    fun provideLocationRetrofit(
//        @Named("generalClient") client: OkHttpClient
//    ): Retrofit =
//        Retrofit.Builder()
//            .client(client)
//            .addConverterFactory(GsonConverterFactory.create())
//            .addCallAdapterFactory(RxJava3CallAdapterFactory.createWithScheduler(Schedulers.io()))
//            .baseUrl(BuildConfig.BASE_URL_LOCATION)
//            .build()
//
//    @Provides
//    @Singleton
//    @Named("weatherRetrofit")
//    fun provideWeatherRetrofit(
//        client: OkHttpClient
//    ): Retrofit =
//        Retrofit.Builder()
//            .client(client)
//            .addConverterFactory(GsonConverterFactory.create())
//            .addCallAdapterFactory(RxJava3CallAdapterFactory.createWithScheduler(Schedulers.io()))
//            .baseUrl(BuildConfig.BASE_URL_WEATHER)
//            .build()
//
//    @Provides
//    @Singleton
//    fun provideLocationApiService(@Named("locationRetrofit") retrofit: Retrofit): LocationApi =
//        retrofit.create(LocationApi::class.java)
//
//    @Provides
//    @Singleton
//    fun provideWeatherApiService(@Named("weatherRetrofit") retrofit: Retrofit): WeatherApi =
//        retrofit.create(WeatherApi::class.java)


}
