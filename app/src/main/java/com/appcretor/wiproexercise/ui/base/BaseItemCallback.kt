package com.appcretor.wiproexercise.ui.base

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil

class BaseItemCallback<T> : DiffUtil.ItemCallback<T>() {
    override fun areItemsTheSame(oldItem: T, newItem: T) = oldItem.toString() == newItem.toString()

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: T, newItem: T) :Boolean {
        return oldItem == newItem
    }
}