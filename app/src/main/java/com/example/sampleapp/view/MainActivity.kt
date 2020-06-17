package com.example.sampleapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sampleapp.R
import com.example.sampleapp.model.Article
import com.example.sampleapp.view.adapter.ArticleListAdapter
import com.example.sampleapp.viewModel.ArticleVIewModel

class MainActivity : AppCompatActivity() {
    lateinit var viewModel: ArticleVIewModel
    lateinit var recyclerView : RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setToolBar();
        recyclerView = findViewById(R.id.rv_article_list)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        viewModel = ViewModelProviders.of(this).get(ArticleVIewModel::class.java)

        viewModel.getArticleList().observe(this, Observer<List<Article>> {
                recyclerView.adapter = ArticleListAdapter(this, it)
        })
//        mProgressBar = progressBar
//        progressBar.visibility = View.VISIBLE
    }

    private fun setToolBar() {
        supportActionBar?.setTitle(getString(R.string.article))
    }
}