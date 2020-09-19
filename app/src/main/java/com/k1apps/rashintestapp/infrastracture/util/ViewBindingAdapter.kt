package com.k1apps.rashintestapp.infrastracture.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.k1apps.rashintestapp.R

@BindingAdapter("imageUrl")
fun imageUrl(img: ImageView, imgUrl: String?) {
    Glide.with(img.context)
        .load(imgUrl)
        .placeholder(R.drawable.ic_launcher_foreground)
        .dontAnimate().into(img);
}