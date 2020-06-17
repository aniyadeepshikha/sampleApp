package com.example.sampleapp.network

import com.example.sampleapp.utils.Constants
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {

    var retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    var instance: ApiService = retrofit.create(ApiService::class.java)
//    val instance: ApiService = Retrofit.Builder().run {
//        val gson = GsonBuilder()
//            .enableComplexMapKeySerialization()
//            .setPrettyPrinting()
//            .create()
//
//        baseUrl(Constants.BASE_URL)
//        addConverterFactory(GsonConverterFactory.create(gson))
//        build()
//    }.create(ApiService::class.java)

}