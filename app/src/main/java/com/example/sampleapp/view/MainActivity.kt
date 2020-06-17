package com.example.sampleapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.ProgressBar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sampleapp.R
import com.example.sampleapp.model.Article
import com.example.sampleapp.view.adapter.ArticleListAdapter
import com.example.sampleapp.viewModel.ArticleVIewModel
import kotlinx.android.synthetic.main.layout_list_item.*

class MainActivity : AppCompatActivity() {
    lateinit var viewModel: ArticleVIewModel
    lateinit var recyclerView : RecyclerView
    lateinit var mProgressBar : LinearLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setToolBar();
        recyclerView = findViewById(R.id.rv_article_list)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        viewModel = ViewModelProviders.of(this).get(ArticleVIewModel::class.java)
        mProgressBar = findViewById(R.id.progress_bar)
        viewModel.getArticleList().observe(this, Observer<List<Article>> {
                recyclerView.adapter = ArticleListAdapter(this, it)
            mProgressBar.visibility = View.GONE
        })
        mProgressBar.visibility = View.VISIBLE
    }

    private fun setToolBar() {
        supportActionBar?.setTitle(getString(R.string.article))
    }
}