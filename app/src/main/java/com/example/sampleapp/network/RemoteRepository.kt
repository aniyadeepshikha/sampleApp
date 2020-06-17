package com.example.sampleapp.network

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.sampleapp.model.Article
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteRepository {

    fun getArticles(): MutableLiveData<List<Article>> {
        val mutableLiveData = MutableLiveData<List<Article>>()

        ApiClient.instance.getArticles()
            .enqueue(object : Callback<List<Article>> {
                override fun onFailure(call: Call<List<Article>>, t: Throwable) {
                    Log.d("Error", "Coudn't get the data")
                }
                override fun onResponse(call: Call<List<Article>>, response: Response<List<Article>>) {
                    if (response.isSuccessful) {
                        mutableLiveData.postValue(response.body())
                    } else {
                        Log.d("Error", "Coudn't get the data")
                    }

                }
            })
        return mutableLiveData
    }

}