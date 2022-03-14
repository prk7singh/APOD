package com.example.apod.data.favorites

import com.example.apod.data.db.Media
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class FavoritesRepository(private val favSource: FavLocalDataSource, private val dispatcher:CoroutineDispatcher) {
    suspend fun getAllFavorites() = withContext(dispatcher){
        favSource.getAllFavorites()
    }

    suspend fun addToFavorites(media: Media) = withContext(dispatcher){
        favSource.addToFavorites(media)
    }
}