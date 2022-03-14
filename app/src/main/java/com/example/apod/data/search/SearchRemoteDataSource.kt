package com.example.apod.data.search

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class SearchRemoteDataSource(private val mediaAPI: SearchAPI, private val dispatcher: CoroutineDispatcher) {
    suspend fun fetchMedia(date:String="") = withContext(dispatcher){
        mediaAPI.fetchMedia(date)
    }
}