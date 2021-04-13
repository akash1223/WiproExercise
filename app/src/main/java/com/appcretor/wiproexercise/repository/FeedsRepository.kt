package com.appcretor.wiproexercise.repository

import com.appcretor.wiproexercise.http.IHTTPService
import com.appcretor.wiproexercise.http.Resource
import com.appcretor.wiproexercise.model.FeedsResponse
import com.appcretor.wiproexercise.utils.LocalizeTextProvider

class FeedsRepository(
    private val httpIService: IHTTPService,
    localizeTextProvider: LocalizeTextProvider
) :
    BaseRepository(localizeTextProvider) {

    suspend fun executeFeed(): Resource<FeedsResponse> {
        return getResult { httpIService.callFeeds() }
    }
}