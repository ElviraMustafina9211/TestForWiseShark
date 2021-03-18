package com.example.testforwiseshark.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.testforwiseshark.databinding.MainActivityBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var mainActivityBinding: MainActivityBinding


    @Inject
    lateinit var imageViewModelFactory: ImageViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        mainActivityBinding = MainActivityBinding.inflate(layoutInflater)
        val view = mainActivityBinding.root
        setContentView(view)

        val imageViewModel = ViewModelProvider(this, imageViewModelFactory).get(
            ImageViewModel::class.java)

        imageViewModel.getImages()

        val recyclerView = mainActivityBinding.recyclerview
        val imageAdapter = ImageAdapter()

        recyclerView.adapter = imageAdapter

        imageViewModel.images.observe(this, { images: List<String> ->
            imageAdapter.setImages(images)
        })
    }
}