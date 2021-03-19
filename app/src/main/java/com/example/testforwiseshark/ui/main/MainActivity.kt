package com.example.testforwiseshark.ui.main

import android.R
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.testforwiseshark.databinding.MainActivityBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: MainActivityBinding

    private var adapter: ImageAdapter? = null

    @Inject
    lateinit var imageViewModelFactory: ImageViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = MainActivityBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val imageViewModel = ViewModelProvider(this, imageViewModelFactory).get(
            ImageViewModel::class.java
        )

        imageViewModel.getImages()

        val recyclerView = binding.recyclerview

        recyclerView.layoutManager = GridLayoutManager(
            this,
            3
        )

        adapter = ImageAdapter()
        recyclerView.adapter = adapter

        recyclerView.addItemDecoration(SpacesItemDecoration(12, 3))

        imageViewModel.images.observe(this, { images: List<String> ->
            adapter?.setImages(images)
        })

        imageViewModel.isLoading.observe(this, { isLoading: Boolean ->
            if (isLoading) {
                binding.progressBar.visibility = View.VISIBLE
            } else {
                binding.progressBar.visibility = View.GONE
            }
        })

        imageViewModel.error.observe(this, { error: Boolean ->
            if (error) {
                binding.noInternetConnection.visibility = View.VISIBLE
            } else {
                binding.noInternetConnection.visibility = View.GONE
            }
        })

        binding.noInternetConnection.setOnClickListener {
            imageViewModel.getImages()
        }
    }
}