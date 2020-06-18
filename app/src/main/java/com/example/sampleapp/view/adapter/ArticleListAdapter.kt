package com.example.sampleapp.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.sampleapp.R
import com.example.sampleapp.model.Article
import com.example.sampleapp.utils.TimeAgo
import de.hdodenhof.circleimageview.CircleImageView
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.*


class ArticleListAdapter(var context : Context, var articleList : MutableList<Article>) : RecyclerView.Adapter<ViewHolder>(){
    private val ITEM = 0
    private val LOADING = 1
    private var isLoadingAdded = false


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        when (viewType) {
            ITEM -> {
                val view: View = LayoutInflater.from(parent.context).inflate(R.layout.layout_list_item,parent,false)
                return ArticleListViewHolder(view)
            }
            LOADING -> {
                val v2: View = inflater.inflate(R.layout.layout_progress_bar, parent, false)
                return LoadingVH(v2)
            }
        }
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.layout_list_item,parent,false)
        return  return ArticleListViewHolder(view)
    }

    override fun getItemCount(): Int {
       return articleList.size
    }
    override fun getItemViewType(position: Int): Int {
        return if (position == articleList.size - 1 && isLoadingAdded) LOADING else ITEM
    }

    override fun onBindViewHolder(holder1: ViewHolder, position: Int) {
        val article: Article = articleList.get(position)
        when (getItemViewType(position)) {
            ITEM -> {
                    val holder: ArticleListViewHolder = holder1 as ArticleListViewHolder
                    holder.userName.text = article.user.get(0).name
                    holder.userDesignation.text = article.user.get(0).designation
                    holder.content.text = article.content
                    if(article.media.size > 0 ) {
                        holder.title.text = article.media.get(0).title
                        holder.uri.text = article.media.get(0).url
                    }

                    holder.likes.text =prettyCount(article.likes) + " Likes"
                    holder.comments.text =prettyCount(article.comments) + " Comments "

                    val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
                    val dateStr =article.createdAt
                    val date: Date = inputFormat.parse(dateStr)

                    holder.createdAt.text = TimeAgo.getTimeAgo(date.time)

                    if(articleList.get(position).media.size > 0){
                        holder.articleImage.visibility = View.VISIBLE
                        Glide.with(context).load(articleList.get(position).media.get(0).image).into(holder.articleImage)
                    }else{
                        holder.articleImage.visibility = View.GONE
                    }
                    Glide.with(context).load(articleList.get(position).user.get(0).avatar).into(holder.userImage);
            }
            LOADING -> {
            }
        }
    }

    class ArticleListViewHolder(item : View) : ViewHolder(item){
        val userName : AppCompatTextView = item.findViewById(R.id.tv_user_name)
        val userDesignation : AppCompatTextView = item.findViewById(R.id.tv_user_designation)
        val content : AppCompatTextView = item.findViewById(R.id.tv_content)
        val title : AppCompatTextView = item.findViewById(R.id.tv_title)
        val uri : AppCompatTextView = item.findViewById(R.id.tv_uri)
        val likes : AppCompatTextView = item.findViewById(R.id.tv_likes)
        val comments : AppCompatTextView = item.findViewById(R.id.tv_comments)
        val createdAt : AppCompatTextView = item.findViewById(R.id.tv_post_time)

        val articleImage : AppCompatImageView = item.findViewById(R.id.iv_content_image)
        val userImage : CircleImageView = item.findViewById(R.id.profile_image)

    }

    class LoadingVH(itemView: View?) : ViewHolder(itemView!!)

    fun prettyCount(number: Double): String? {
        val suffix = charArrayOf(' ', 'k', 'M', 'B', 'T', 'P', 'E')
        val value = Math.floor(Math.log10(number.toDouble())).toInt()
        val base = value / 3
        return if (value >= 3 && base < suffix.size) {
            DecimalFormat("#0.0").format(
                number / Math.pow(
                    10.0,
                    base * 3.toDouble()
                )
            ) + suffix[base]
        } else {
            DecimalFormat("#,##0").format(number)
        }
    }

    fun add(article: Article) {
        articleList.add(article)
        notifyItemInserted(articleList.size - 1)
    }

    fun addAll(articleList: List<Article>) {
        for (article in articleList) {
            add(article)
        }
    }

    fun addLoadingFooter() {
        isLoadingAdded = true
        add(Article())
    }
    fun getItem(position: Int): Article? {
        return articleList.get(position)
    }
    fun removeLoadingFooter() {
        isLoadingAdded = false
        val position: Int = articleList.size - 1
        val item: Article? = getItem(position)
        if (item != null) {
            articleList.removeAt(position)
            notifyItemRemoved(position)
        }
    }


}