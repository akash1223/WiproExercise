package com.appcretor.wiproexercise.http

import com.appcretor.wiproexercise.model.FeedsResponse
import retrofit2.Response
import retrofit2.http.GET

interface IHTTPService {
    @GET(HttpConstants.METHOD_GET_FEED)
    suspend fun callGetUserProfile(): Response<FeedsResponse>
}