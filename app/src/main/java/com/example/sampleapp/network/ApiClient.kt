package com.example.sampleapp.network

import com.example.sampleapp.utils.Constants
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    val instance: ApiService = Retrofit.Builder().run {
        val gson = GsonBuilder()
            .enableComplexMapKeySerialization()
            .setPrettyPrinting()
            .create()

        baseUrl(Constants.BASE_URL)
        addConverterFactory(GsonConverterFactory.create(gson))
//        client(createRequestInterceptorClient())
        build()
    }.create(ApiService::class.java)

}