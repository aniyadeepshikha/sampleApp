package com.example.sampleapp.network

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.sampleapp.model.Article
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object RemoteRepository {

    fun getArticles(pageIndex : String, limit : String): MutableLiveData<MutableList<Article>> {
        val mutableLiveData = MutableLiveData<MutableList<Article>>()
        System.out.println("PAgeIndex: "+pageIndex)
        ApiClient.instance.getArticles(pageIndex, limit)
            .enqueue(object : Callback<MutableList<Article>> {
                override fun onFailure(call: Call<MutableList<Article>>, t: Throwable) {
                    Log.d("Error", "Coudn't get the data")
                }
                override fun onResponse(call: Call<MutableList<Article>>, response: Response<MutableList<Article>>) {
                    if (response.isSuccessful) {
                        Log.d("RemoteRepository: ",""+response.toString())
                        mutableLiveData.postValue(response.body())
                    } else {
                        Log.d("Error", "Coudn't get the data")
                    }

                }
            })
        return mutableLiveData
    }

}