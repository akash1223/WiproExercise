package com.appcretor.wiproexercise.di

import android.content.Context
import com.appcretor.wiproexercise.BuildConfig
import com.appcretor.wiproexercise.http.IHTTPService
import com.appcretor.wiproexercise.http.NetworkConnectionInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import java.util.concurrent.TimeUnit

val httpModule = module {
    val tag = "Retrofit: "

    fun initRetrofit(httpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BuildConfig.BASE_URL)
            .client(httpClient)
            .build()
    }

    fun initHttpClient(netConInterceptor: NetworkConnectionInterceptor): OkHttpClient {
        val okHttpClient = OkHttpClient.Builder()
        if (BuildConfig.DEBUG) {
            val logging = HttpLoggingInterceptor {
                Timber.i("$tag  $it")
            }
            logging.level = HttpLoggingInterceptor.Level.BODY
            okHttpClient.addInterceptor(logging)
        }
        okHttpClient.addInterceptor(netConInterceptor)
        okHttpClient.connectTimeout(50, TimeUnit.SECONDS)
        okHttpClient.readTimeout(50, TimeUnit.SECONDS)
        return okHttpClient.build()
    }

    fun initRetrofitHttpService(retrofit: Retrofit): IHTTPService {
        return retrofit.create(IHTTPService::class.java)
    }


    single { NetworkConnectionInterceptor(get()) }
    single { initHttpClient(get()) }
    single { initRetrofit(get()) }
    single { initRetrofitHttpService(get()) }
}
