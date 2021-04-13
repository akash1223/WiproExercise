package com.appcretor.wiproexercise.di

import com.appcretor.wiproexercise.utils.LocalizeTextProvider
import com.appcretor.wiproexercise.utils.NetworkManager
import org.koin.dsl.module


val UtilsManagerModule = module {
    single { NetworkManager(get()) }
    single { LocalizeTextProvider(get()) }
}