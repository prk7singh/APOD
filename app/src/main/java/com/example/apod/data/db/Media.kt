package com.example.apod.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorites")
data class Media(
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
    fun getTodaysData(): TodayData {
        return TodayData(date,explanation,hdurl,mediaType,serviceVersion,title,url,copyright)
    }
}
