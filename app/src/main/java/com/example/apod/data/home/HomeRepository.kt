package com.example.apod.data.home

import com.example.apod.data.db.TodayData
import com.example.apod.data.search.SearchRemoteDataSource
import com.example.apod.data.db.Media

class HomeRepository(private val dataSource: SearchRemoteDataSource, private val localDataSource: HomeLocalDataSource) {

    /**
     * 1.Look for today's data in db
     * 2.If found, use it
     * 3.else fetch from server and store in db
     */
    suspend fun getTodaysData(): Media? {
        val data = localDataSource.getTodaysData()
        return if (data==null){
            val result = dataSource.fetchMedia()
            if (result != null) {
                saveTodaysData(result.getTodaysData())
            }
            result
        }else{
            data.getMedia()
        }
    }

    suspend fun saveTodaysData(media: TodayData) = localDataSource.saveTodaysData(media)
}