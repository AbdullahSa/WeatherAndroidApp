package com.abdullah.weatherapp.domain.di

import com.abdullah.weatherapp.domain.service.rest.CurrentWeatherService
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


/**
 * Created by abdullah on 24.1.2018.
 */
@Module
class NetworkModule {

    var url: String
    var key: String

    constructor(url: String, key: String) {
        this.url = url
        this.key = key
    }

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return GsonBuilder().setLenient().create();
    }

    @Provides
    @Singleton
    fun provideHttpClient(): OkHttpClient {
        var okHttpBuilder = OkHttpClient.Builder().addInterceptor(Interceptor { chain ->

            val original = chain.request()
            val originalHttpUrl = original.url()

            val url = originalHttpUrl.newBuilder()
                    .addQueryParameter("appid", this.key)
                    .build()

            val requestBuilder = original.newBuilder()
                    .url(url)

            val request = requestBuilder.build()
            chain.proceed(request)
        })
        return okHttpBuilder.build()

    }


    @Provides
    @Singleton
    fun provideRetrofit(gson: Gson, okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder().addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(okHttpClient)
                .baseUrl(url)
                .build()
    }

    @Provides
    @Singleton
    fun provideCurrentWeatherService(retrofit: Retrofit): CurrentWeatherService {
        return retrofit.create(CurrentWeatherService::class.java)
    }

}