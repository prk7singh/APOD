package com.example.apod.data.favorites

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.apod.data.db.Media

@Dao
interface FavoritesDao {
    @Query("SELECT * from favorites")
    fun getAll(): List<Media>

    @Insert
    fun insert(media: Media)
}
