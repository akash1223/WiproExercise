package com.appcretor.wiproexercise.utils

import android.content.Context
import com.appcretor.wiproexercise.R

/**
 * This class provide string messages
 */
class LocalizeTextProvider(var context: Context) {

    fun getNoInternetMessage(): String {
        return context.getString(R.string.no_internet)
    }
    fun getErrorMessage(): String {
        return context.getString(R.string.error)
    }
    fun getSomethingWrongMessage(): String {
        return context.getString(R.string.something_went_wrong)
    }
}