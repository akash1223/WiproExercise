package com.appcretor.wiproexercise


import android.app.Application
import com.appcretor.wiproexercise.di.UtilsManagerModule
import com.appcretor.wiproexercise.di.httpModule
import com.appcretor.wiproexercise.di.repositoryModule
import com.appcretor.wiproexercise.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import timber.log.Timber


class MainApp : Application() {

    companion object {
        private var INSTANCE: MainApp? = null
        fun instance() = INSTANCE
    }

    override fun onCreate() {
        super.onCreate()

        INSTANCE = this
        startKoin {
            androidLogger()
            androidContext(this@MainApp)
            modules(listOf(httpModule, UtilsManagerModule, viewModelModule, repositoryModule))
        }
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}