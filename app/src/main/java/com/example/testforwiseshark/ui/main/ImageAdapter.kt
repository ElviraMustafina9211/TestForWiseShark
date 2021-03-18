package com.example.testforwiseshark.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.testforwiseshark.R

class ImageAdapter (): RecyclerView.Adapter<ImageItemHolder>() {

    private var images: List<String> = emptyList()

    fun setImages(images: List<String>) {
        this.images = images
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageItemHolder {
        val itemView: View = LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_item, parent, false)
        return ImageItemHolder(itemView)
    }

    override fun onBindViewHolder(holder: ImageItemHolder, position: Int) {
        holder.bind(images[position])
    }

    override fun getItemCount(): Int {
        return images.size
    }
}