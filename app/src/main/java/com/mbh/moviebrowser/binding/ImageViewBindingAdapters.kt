package com.mbh.moviebrowser.binding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("remoteImage")
fun ImageView.bindRemoteImage(url: String?) {
    url ?: return

    Glide.with(this)
        .load("https://image.tmdb.org/t/p/w342$url")
        .into(this)
}
