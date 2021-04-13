package com.appcretor.wiproexercise.utils

import com.appcretor.wiproexercise.http.HttpConstants
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.RecordedRequest

object SuccessResponseDispatcher {

    fun  dispatcher(vararg pathValue: Any = arrayOf()) = object : Dispatcher() {
        @Throws(InterruptedException::class)
        override fun dispatch(request: RecordedRequest): MockResponse {

            val requestPath = request.path?.removePrefix("/")
            return when (requestPath) {
                HttpConstants.METHOD_GET_FEED -> {
                    getMockResponseFromJGON("feed.json")
                }
                else -> {
                    MockResponse().setResponseCode(404)}
            }
        }
    }

    private fun getMockResponseFromJGON(JsonString: String): MockResponse {
        val successResponse =
            MockResponseFileReader(JsonString).content
        return getMockResponse(HttpConstants.STATUS_CODE_OK,successResponse)
    }

    private fun getMockResponse(statusCode: Int, response: String): MockResponse {
        return MockResponse().setResponseCode(statusCode)
            .setBody(
                response
            ).addHeader("Content-Type", "application/json; charset=utf-8")
            .addHeader("Cache-Control", "no-cache")
    }
}