package com.ms.news.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ms.news.databinding.ItemArticlePreviewBinding
import com.ms.news.models.Article

class NewsAdapter: RecyclerView.Adapter<NewsAdapter.ArticleViewHolder>() {
    inner class ArticleViewHolder(private val binding:ItemArticlePreviewBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(article: Article){
            binding.apply {
                tvSource.text=article.source?.name
                tvTitle.text=article.title
                tvDescription.text=article.description
                tvPublishedAt.text=article.publishedAt
                Glide.with(binding.root).load(article.urlToImage).into(ivArticleImage)
            }
        }
    }

    private val diffCallBack = object :DiffUtil.ItemCallback<Article>(){
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.url==newItem.url
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
          return oldItem==newItem
        }

    }

     val differ=AsyncListDiffer(this,diffCallBack)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        return ArticleViewHolder(
            ItemArticlePreviewBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        )
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
       val article=differ.currentList[position]
        holder.bind(article)
        holder.itemView.setOnClickListener{
            onItemClickListener?.let {
                it(article)
            }
        }

    }

    override fun getItemCount(): Int {
       return differ.currentList.size
    }

   private var onItemClickListener:((Article)->Unit)? = null

    fun setOnItemClickListener(listener:(Article)->Unit){
        onItemClickListener=listener
    }
}