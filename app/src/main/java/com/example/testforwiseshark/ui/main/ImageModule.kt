package com.example.testforwiseshark.ui.main

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object ImageModule {

    @Provides
    fun imageRepository(imageApi: ImageApi): ImageRepository {
        return ImageRepositoryImpl(imageApi)
    }

    @Provides
    fun imageViewModelFactory(imageRepository: ImageRepository): ImageViewModelFactory {
        return ImageViewModelFactory(imageRepository)
    }

    @Provides
    fun imageApi(): ImageApi {
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://www.dropbox.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
        return retrofit.create(ImageApi::class.java)
    }
}