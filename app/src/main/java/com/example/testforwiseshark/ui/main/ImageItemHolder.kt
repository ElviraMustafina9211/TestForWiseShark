package com.example.testforwiseshark.ui.main

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.request.RequestOptions
import com.example.testforwiseshark.R

class ImageItemHolder(view: View) : RecyclerView.ViewHolder(view) {

    private lateinit var imageUrl: String

    private val ivImage: ImageView = view as ImageView

    fun bind(imageUrl: String) {
        this.imageUrl = imageUrl
        Glide.with(itemView.context)
            .load(imageUrl)
            .placeholder(R.drawable.placeholder)
            .apply(RequestOptions().transform(CenterCrop()))
            .into(ivImage)
    }
}