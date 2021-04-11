package com.appcretor.wiproexercise.model


import android.annotation.SuppressLint
import kotlinx.android.parcel.Parcelize
import android.os.Parcelable

@SuppressLint("ParcelCreator")
@Parcelize
data class FeedsResponse(
    val rows: List<Feed?>?,
    val title: String?
) : Parcelable {
    @SuppressLint("ParcelCreator")
    @Parcelize
    data class Feed(
        val description: String?,
        val imageHref: String?,
        val title: String?
    ) : Parcelable
}