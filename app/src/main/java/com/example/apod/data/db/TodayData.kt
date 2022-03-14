package com.example.apod.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "today")
data class TodayData(
    @PrimaryKey
    var date:String,
    var explanation:String,
    var hdurl:String?,
    var mediaType:String?,
    var serviceVersion:String?,
    var title:String,
    var url:String,
    var copyright:String?
){
    fun getMedia(): Media {
        return Media(date,explanation,hdurl,mediaType,serviceVersion,title,url,copyright)
    }
}
