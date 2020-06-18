package com.example.sampleapp.network

import com.example.sampleapp.model.Article
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("jet2/api/v1/blogs")
    fun getArticles(@Query("page") pageIndex : String,
                    @Query("limit") limit : String) : Call<MutableList<Article>>
}