package com.ms.news.db

import androidx.room.TypeConverter
import com.ms.news.models.Source

class Converters {

    @TypeConverter
    fun fromSource(source: Source):String{
        return source.name
    }

    @TypeConverter
    fun toSource(name:String):Source{
        return Source(id=name,name=name)
    }
}