package com.example.mvvmcountries.util

import android.content.Context
import android.widget.ImageView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.mvvmcountries.R

fun ImageView.downloadFromURL(url : String?, placeHolderProgressBar : CircularProgressDrawable){

    val options = RequestOptions()
        .placeholder(placeHolderProgressBar)
        .error(R.drawable.ic_launcher_foreground)

    Glide.with(context)
        .setDefaultRequestOptions(options)
        .load(url)
        .into(this)

}

fun placeHolderProgressBar(context : Context) : CircularProgressDrawable {

    return CircularProgressDrawable(context).apply {
        strokeWidth = 8f
        centerRadius = 40f
        start()
    }
}