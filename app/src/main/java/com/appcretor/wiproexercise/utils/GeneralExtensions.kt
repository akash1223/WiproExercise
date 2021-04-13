package com.lysn.clinician.utility.extensions


import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.appcretor.wiproexercise.http.Resource


fun <T> LiveData<T>.observeOnce(lifecycleOwner: LifecycleOwner, observer: Observer<T>) {
    observe(lifecycleOwner, object : Observer<T> {
        override fun onChanged(t: T?) {
            observer.onChanged(t)
            removeObserver(this)
        }
    })
}

fun <T> LiveData<Resource<T>>.observeNetworkCall(
    lifecycleOwner: LifecycleOwner,
    observer: Observer<Resource<T>>
) {
    observe(lifecycleOwner, object : Observer<Resource<T>> {
        override fun onChanged(resource: Resource<T>) {

            if (resource.status != Resource.Status.LOADING) {
                observer.onChanged(resource)
                removeObserver(this)
            } else {
                observer.onChanged(resource)
            }
        }
    })
}