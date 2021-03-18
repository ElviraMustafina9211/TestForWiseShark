package com.example.testforwiseshark.ui.main

import io.reactivex.Single

interface ImageRepository {
    fun loadImages(): Single<List<String>>
}