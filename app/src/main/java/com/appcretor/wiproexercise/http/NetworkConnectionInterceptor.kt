package com.appcretor.wiproexercise.http


import com.appcretor.wiproexercise.utils.NetworkManager
import com.appcretor.wiproexercise.utils.NoInternetException
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class NetworkConnectionInterceptor(private val networkManager: NetworkManager) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {

        if (!networkManager.isNetworkAvailable) {

            throw NoInternetException()
        }
        val builder: Request.Builder = chain.request().newBuilder()
        return chain.proceed(builder.build())
    }
}