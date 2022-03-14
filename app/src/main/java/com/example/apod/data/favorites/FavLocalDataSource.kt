package com.example.apod.data.favorites

import android.content.Context
import android.database.sqlite.SQLiteConstraintException
import android.util.Log
import androidx.room.Room
import com.example.apod.data.db.AppDatabase
import com.example.apod.data.db.DB_NAME
import com.example.apod.data.db.Media

class FavLocalDataSource(private val context: Context) {

    val db by lazy { Room.databaseBuilder(
        context,
        AppDatabase::class.java, DB_NAME
    ).build() }

    fun addToFavorites(media: Media) {
        try {
            db.favoritesDao().insert(media)
        }catch (exception:SQLiteConstraintException){
            exception.printStackTrace()
            Log.e("TAG","element already exists")
        }
    }

    fun getAllFavorites():List<Media>{
        return db.favoritesDao().getAll()
    }
}