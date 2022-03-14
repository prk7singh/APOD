package com.example.apod.data.home

import android.content.Context
import androidx.room.Room
import com.example.apod.data.db.AppDatabase
import com.example.apod.data.db.DB_NAME
import com.example.apod.data.db.TodayData
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class HomeLocalDataSource(private val context: Context, private val dispatcher: CoroutineDispatcher) {

    val db by lazy { Room.databaseBuilder(
        context,
        AppDatabase::class.java, DB_NAME
    ).build() }

    suspend fun getTodaysData() = withContext(dispatcher){
        db.homeDao().getTodaysData()
    }

    suspend fun saveTodaysData(media: TodayData) = withContext(dispatcher){
        //delete all previous data and store today's data
        db.homeDao().deleteAll()
        db.homeDao().saveTodaysData(media)
    }
}