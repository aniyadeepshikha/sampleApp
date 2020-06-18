package com.example.sampleapp.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sampleapp.model.Article
import com.example.sampleapp.network.RemoteRepository

class ArticleVIewModel : ViewModel() {

    var articleList = MutableLiveData<MutableList<Article>>()

    fun getArticleList(pageIndex : String, limit : String) : LiveData<MutableList<Article>>{
        articleList = RemoteRepository.getArticles(pageIndex, limit)
        return articleList
    }
}