package com.ms.news.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.ms.news.models.Article
import java.util.concurrent.locks.Lock

@TypeConverters(Converters::class)
@Database(
    entities = [Article::class],
    version = 1
)
abstract class ArticleDatabase : RoomDatabase() {

    abstract fun getAllArticlesDao(): ArticlesDao

    companion object {
        @Volatile
        private var INSTANCE: ArticleDatabase? = null
        private val LOCK=Any()

//
//        fun createDatabase(context: Context): ArticleDatabase {
//            return INSTANCE ?: synchronized(this) {
//                val instance = Room.databaseBuilder(
//                    context.applicationContext,
//                    ArticleDatabase::class.java,
//                    "article_db.db"
//                )
//                    .build()
//                INSTANCE=instance
//                instance
//            }
//        } // via object function

        operator fun invoke(context: Context)= INSTANCE ?: synchronized(LOCK){
            INSTANCE ?: createDatabase(context).also{
                INSTANCE=it
            }
        }

        private fun createDatabase(context: Context)= Room.databaseBuilder(
                    context.applicationContext,
                    ArticleDatabase::class.java,
                    "article_db.db"
                ).fallbackToDestructiveMigration()
            .build() // via object
    }
}