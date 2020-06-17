package com.example.sampleapp.network

import com.example.sampleapp.model.Article
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ApiService {
    @GET("jet2/api/v1/blogs?page=1&limit=10")
    fun getArticles() : Call<List<Article>>
}