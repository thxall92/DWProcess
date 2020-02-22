package com.example.dwprocess.di

import com.example.dwprocess.BuildConfig
import com.example.dwprocess.network.api.DWApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

const val NETWORK_OK_HTTP_CLIENT = "okHttpClient"
const val NETWORK_RETROFIT_ADAPTER = "retrofitAdapter"

val networkModule = module {
    single(named(NETWORK_OK_HTTP_CLIENT)){
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = if (BuildConfig.DEBUG) {
                    HttpLoggingInterceptor.Level.BODY
                } else {
                    HttpLoggingInterceptor.Level.NONE
                }
            })
            .build()
    }
    single(named(name = NETWORK_RETROFIT_ADAPTER)) {
        Retrofit.Builder()
            .baseUrl(BuildConfig.API_HOST_URL)
            .client(get(named(NETWORK_OK_HTTP_CLIENT)))
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }
    single {
        (get(named(NETWORK_RETROFIT_ADAPTER)) as Retrofit).create(DWApi::class.java)
    }
}