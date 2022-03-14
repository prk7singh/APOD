package com.example.apod.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.apod.data.favorites.FavoritesDao
import com.example.apod.data.home.HomeDao

const val DB_NAME = "apod-db"

@Database(entities = [Media::class, TodayData::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun favoritesDao(): FavoritesDao
    abstract fun homeDao(): HomeDao
}