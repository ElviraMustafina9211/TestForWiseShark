package com.example.testforwiseshark.ui.main

import io.reactivex.Single
import retrofit2.http.GET

interface ImageApi {
    @GET("s/4x1txil6hc7s11p/images.json?dl=1")
    fun getImages(): Single<List<String>>
}