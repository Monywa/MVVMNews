package com.ms.news.repository

import com.ms.news.api.RetrofitInstance
import com.ms.news.db.ArticleDatabase
import com.ms.news.models.Article

class NewsRepository(
    private val db: ArticleDatabase
) {

    suspend fun getBreakingNews(countryCode: String, pageNumber: Int) =
        RetrofitInstance.api.getBreakingNews(countryCode, pageNumber)

    suspend fun searchNews(searchQuery: String, pageNumber: Int) =
        RetrofitInstance.api.searchForNews(searchQuery, pageNumber)

    fun getSavedNews() = db.getAllArticlesDao().getAllArticles()

    suspend fun deleteArticle(article: Article)=db.getAllArticlesDao().delete(article)

    suspend fun upsert(article: Article)=db.getAllArticlesDao().upsert(article)
}