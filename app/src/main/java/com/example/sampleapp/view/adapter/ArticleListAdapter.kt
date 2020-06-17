package com.example.sampleapp.view.adapter

import android.content.Context
import android.text.format.DateUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.sampleapp.R
import com.example.sampleapp.model.Article
import com.example.sampleapp.utils.TimeAgo
import de.hdodenhof.circleimageview.CircleImageView
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.*

class ArticleListAdapter(var context : Context, var articleList : List<Article>) : RecyclerView.Adapter<ArticleListAdapter.ArticleListViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.layout_list_item,parent,false)
        return ArticleListViewHolder(view)
    }

    override fun getItemCount(): Int {
       return articleList.size
    }

    override fun onBindViewHolder(holder: ArticleListViewHolder, position: Int) {
        holder.userName.text = articleList?.get(position).user?.get(0)?.name?: "--"
        holder.userDesignation.text = articleList?.get(position).user?.get(0)?.designation?: "--"
        holder.content.text = articleList?.get(position).content?: "--"
        holder.title.text = articleList?.get(position).media?.get(0)?.title?: "--"
        holder.uri.text = articleList?.get(position).media?.get(0)?.url?: "--"
        holder.likes.text =prettyCount(articleList?.get(position).likes) + " Likes"
        holder.comments.text =prettyCount(articleList?.get(position).comments) + " Comments "

        val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
        val dateStr = articleList.get(position).createdAt
        val date: Date = inputFormat.parse(dateStr)

        holder.createdAt.text = TimeAgo.getTimeAgo(date.time)

        if(articleList.get(position).media!=null && articleList.get(position).media.get(0).image!=null){
            holder.articleImage.visibility = View.VISIBLE
            Glide.with(context).load(articleList.get(position).media.get(0).image).into(holder.articleImage)
        }else{
            holder.articleImage.visibility = View.GONE
        }
        Glide.with(context).load(articleList.get(position).user.get(0).avatar).into(holder.userImage);

    }

    class ArticleListViewHolder(item : View) : RecyclerView.ViewHolder(item){
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

}