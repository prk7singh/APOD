package com.example.apod.ui.favorites

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apod.data.favorites.FavLocalDataSource
import com.example.apod.data.favorites.FavoritesRepository
import com.example.apod.data.db.Media
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FavViewModel : ViewModel() {

    private val _favList = MutableLiveData<List<Media>>()
    val favList: LiveData<List<Media>> = _favList

    fun getFavList(context: Context){
        viewModelScope.launch {
            _favList.value =
                FavoritesRepository(
                    FavLocalDataSource(context),
                    Dispatchers.IO).getAllFavorites()
        }
    }
}