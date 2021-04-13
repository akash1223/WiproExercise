package com.appcretor.wiproexercise.utils

import android.content.Context
import android.net.ConnectivityManager


/**
 *  This class contains method to check internet connection  status
 */

class NetworkManager(private var applicationContext: Context) {

    val isNetworkAvailable: Boolean
        get() {
            val connectivityManager =
                applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val nwInfo = connectivityManager.activeNetworkInfo
            return nwInfo != null && nwInfo.isConnected
        }
}