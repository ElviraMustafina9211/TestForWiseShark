package com.example.testforwiseshark.ui.main

import io.reactivex.Single

class ImageRepositoryImpl (private val imageApi: ImageApi) : ImageRepository {
    override fun loadImages(): Single<List<String>> {
        return imageApi.getImages()
    }
}