package com.example.temuin.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private val BASE_URL = "https://asia-southeast2-arunika-b21-cap0049.cloudfunctions.net/"

    val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val apiInstance = retrofit.create(RecommendationApi::class.java)
}