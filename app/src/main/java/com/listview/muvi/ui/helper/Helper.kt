package com.listview.muvi.ui.helper

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

object Helper {
    fun ImageView.load(model: String?){
        Glide.with(this)
            .load("https://image.tmdb.org/t/p/w500${model}")
            .into(this)
    }
}