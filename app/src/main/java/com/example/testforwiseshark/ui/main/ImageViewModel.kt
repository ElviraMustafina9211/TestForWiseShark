package com.example.testforwiseshark.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class ImageViewModel (private val imageRepository: ImageRepository) : ViewModel() {

    private val _images: MutableLiveData<List<String>> = MutableLiveData<List<String>>()
    val images: LiveData<List<String>> = _images

    private var disposable: Disposable? = null
    override fun onCleared() {
        super.onCleared()
        disposable?.dispose()
    }

    fun getImages() {
        disposable = imageRepository
            .loadImages()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe ({ images ->
                _images.value = images
            }, { throw it })
    }
}