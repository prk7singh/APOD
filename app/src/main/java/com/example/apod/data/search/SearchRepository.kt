package com.example.apod.data.search

class SearchRepository(private val dataSource: SearchRemoteDataSource) {
    suspend fun fetchMedia(date:String="") = dataSource.fetchMedia(date)
}