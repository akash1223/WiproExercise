package com.appcretor.wiproexercise.ui.feed

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.appcretor.wiproexercise.http.Resource
import com.appcretor.wiproexercise.model.FeedsResponse
import com.appcretor.wiproexercise.repository.FeedsRepository
import kotlinx.coroutines.launch

class FeedViewModel(private val feedsRepository: FeedsRepository) : ViewModel() {


    private var _mFeedData = MutableLiveData<Resource<FeedsResponse>>()
    val mFeedData: LiveData<Resource<FeedsResponse>>
        get() = _mFeedData


    init {
        fetchFeedsData()
    }
    fun fetchFeedsData() {
        _mFeedData.value = (Resource.loading(null))
        viewModelScope.launch {

            val response =feedsRepository.executeFeed()
            _mFeedData.postValue(response)
        }
    }


}