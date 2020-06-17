package com.example.sampleapp.network

import com.example.sampleapp.model.Article
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ApiService {
    @GET
    fun getArticles() : Call<List<Article>>
}