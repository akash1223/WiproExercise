package com.appcretor.wiproexercise.di


import com.appcretor.wiproexercise.repository.FeedsRepository
import org.koin.dsl.module

val repositoryModule = module {
    single { FeedsRepository(get(),get()) }
}