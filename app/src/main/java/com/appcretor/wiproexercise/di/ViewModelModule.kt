package com.appcretor.wiproexercise.di


import com.appcretor.wiproexercise.ui.feed.FeedViewModel
import org.koin.dsl.module
import org.koin.androidx.viewmodel.dsl.viewModel

val viewModelModule = module {

    viewModel { FeedViewModel() }
}