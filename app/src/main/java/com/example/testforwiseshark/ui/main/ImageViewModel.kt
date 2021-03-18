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

    private val _isLoading: MutableLiveData<Boolean> = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _error: MutableLiveData<Boolean> = MutableLiveData<Boolean>()
    val error: LiveData<Boolean> = _error

    private var disposable: Disposable? = null
    override fun onCleared() {
        super.onCleared()
        disposable?.dispose()
    }

    fun getImages() {
        _isLoading.value = true
        _error.value = false
        disposable = imageRepository
            .loadImages()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe ({ images ->
                _isLoading.value = false
                _images.value = images
            }, { _error.value = true; _isLoading.value = false  })
    }
}