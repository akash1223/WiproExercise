package com.appcretor.wiproexercise.model


import android.annotation.SuppressLint
import kotlinx.android.parcel.Parcelize
import android.os.Parcelable

@SuppressLint("ParcelCreator")
@Parcelize
data class FeedsResponse(
    val rows: List<Feed?>? = null,
    val title: String? = null
) : Parcelable {
    @SuppressLint("ParcelCreator")
    @Parcelize
    data class Feed(
        val description: String? = null,
        val imageHref: String? = null,
        val title: String? = null
    ) : Parcelable
}