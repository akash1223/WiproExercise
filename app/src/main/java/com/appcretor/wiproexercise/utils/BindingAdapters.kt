package com.appcretor.wiproexercise.utils

import android.content.Context
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
import com.bumptech.glide.request.target.DrawableImageViewTarget
import com.bumptech.glide.request.target.Target
import timber.log.Timber
import javax.sql.DataSource


class BindingAdapters(context: Context) {

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
                    .listener(object: RequestListener<Drawable>{
                        override fun onLoadFailed(
                            e: GlideException?,
                            model: Any?,
                            target: Target<Drawable>?,
                            isFirstResource: Boolean
                        ): Boolean {
                            Timber.e("BindingAdapters:Image Load Failed")
                            view.visibility = View.GONE
                            return false
                        }

                        override fun onResourceReady(
                            resource: Drawable?,
                            model: Any?,
                            target: Target<Drawable>?,
                            dataSource: com.bumptech.glide.load.DataSource?,
                            isFirstResource: Boolean
                        ): Boolean {
                           Timber.d("BindingAdapters: Image Loaded")

                            return false
                        }

                    })
                    .into(view)
            }
        }
    }
}