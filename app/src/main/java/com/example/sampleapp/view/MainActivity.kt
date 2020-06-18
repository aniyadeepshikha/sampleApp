package com.example.sampleapp.view

import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sampleapp.R
import com.example.sampleapp.model.Article
import com.example.sampleapp.utils.PaginationScrollListener
import com.example.sampleapp.view.adapter.ArticleListAdapter
import com.example.sampleapp.viewModel.ArticleVIewModel


class MainActivity : AppCompatActivity() {
    lateinit var viewModel: ArticleVIewModel
    lateinit var recyclerView : RecyclerView
    lateinit var mProgressBar : LinearLayout

    private val totalPages = 5
    private var isLoading = false
    private var isLastPage = false
        get() {
            return field
        }
    private var currentPage = 1
    lateinit var adapter : ArticleListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setToolBar();
        recyclerView = findViewById(R.id.rv_article_list)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        viewModel = ViewModelProviders.of(this).get(ArticleVIewModel::class.java)
        mProgressBar = findViewById(R.id.progress_bar)
        viewModel.getArticleList(currentPage.toString(), "10").observe(this, Observer<MutableList<Article>> {
             adapter = ArticleListAdapter(this, it)
                recyclerView.adapter = adapter
            mProgressBar.visibility = View.GONE
            if (currentPage <= totalPages) adapter.addLoadingFooter();
            else isLastPage = true;
        })
        mProgressBar.visibility = View.VISIBLE

        recyclerView.addOnScrollListener(object : PaginationScrollListener(recyclerView.layoutManager as LinearLayoutManager) {
            override fun loadMoreItems() {
                isLoading = true
                currentPage += 1
                loadNextPage()
            }
            override fun getTotalPageCount(): Int {
                return totalPages
            }

            override fun isLastPage(): Boolean {
                return isLastPage
            }

            override fun isLoading(): Boolean {
                return isLoading
            }
        })
    }
    private fun loadNextPage() {
        viewModel.getArticleList(currentPage.toString(), "10").observe(this, Observer<MutableList<Article>> {

            var articleList : MutableList<Article> = it
            adapter.removeLoadingFooter()
            isLoading = false

            adapter.addAll(articleList)
            if (currentPage != totalPages) adapter.addLoadingFooter() else isLastPage = true

        })



    }

    private fun setToolBar() {
        supportActionBar?.setTitle(getString(R.string.article))
    }
}