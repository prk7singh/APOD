package com.example.apod.data.home

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.apod.data.db.TodayData

@Dao
interface HomeDao {
    @Query("SELECT * from today WHERE date = date('now')")
    fun getTodaysData(): List<TodayData>?

    @Insert
    fun saveTodaysData(media: TodayData)

    @Query("Delete from today")
    fun deleteAll()
}
