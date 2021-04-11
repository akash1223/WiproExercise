package com.appcretor.wiproexercise.utils

import com.appcretor.wiproexercise.http.HttpConstants
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.RecordedRequest

object FailureResponseDispatcher {


    fun  dispatcher(vararg pathValue: Any = arrayOf()) = object : Dispatcher() {
        @Throws(InterruptedException::class)
        override fun dispatch(request: RecordedRequest): MockResponse {

            val requestPath = request.path?.removePrefix("/")
            return when (requestPath) {
                HttpConstants.METHOD_GET_FEED -> {
                    getMockResponse(HttpConstants.STATUS_CODE_UNAUTHORIZED_401, "Error for load data")
                }

                else -> MockResponse().setResponseCode(404)
            }
        }
    }
    private fun getMockResponseFromJGON(JsonString: String,statusCode: Int = HttpConstants.STATUS_CODE_UNAUTHORIZED_401): MockResponse {
        val successResponse =
            MockResponseFileReader(JsonString).content
        return getMockResponse(statusCode,successResponse)
    }

    private fun getMockResponse(statusCode: Int, response: String): MockResponse {
        return MockResponse().setResponseCode(statusCode)
            .setBody(
                response
            ).addHeader("Content-Type", "application/json; charset=utf-8")
            .addHeader("Cache-Control", "no-cache")
    }
}