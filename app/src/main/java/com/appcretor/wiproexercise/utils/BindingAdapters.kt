package com.appcretor.wiproexercise.utils

import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingAdapter
import com.appcretor.wiproexercise.R
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import timber.log.Timber

@lombok.Generated
class BindingAdapters {

    companion object {
        @BindingAdapter("app:layoutPercentWidth")
        @JvmStatic
        fun layoutPercentWidth(view: View, height: Float) {
            val lp: ConstraintLayout.LayoutParams =
                view.layoutParams as ConstraintLayout.LayoutParams
            lp.matchConstraintPercentWidth = height
            view.layoutParams = lp
            view.postInvalidate()
        }

        @BindingAdapter("loadImage")
        @JvmStatic
        fun loadImage(view: ImageView, imageUrl: String?) {

            if(!imageUrl.isNullOrEmpty()) {
                Glide.with(view.context)
                    .load(imageUrl).apply(RequestOptions()).fitCenter()
                    .placeholder(R.drawable.icon_thumbnail)
                    .error(R.drawable.icon_thumbnail)
                    .into(view)
            }
        }
    }
}