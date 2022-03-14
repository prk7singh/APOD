package com.example.apod.ui.home

import android.content.Context
import android.net.ConnectivityManager
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apod.data.favorites.FavLocalDataSource
import com.example.apod.data.favorites.FavoritesRepository
import com.example.apod.data.home.HomeLocalDataSource
import com.example.apod.data.home.HomeRepository
import com.example.apod.data.search.SearchAPIImpl
import com.example.apod.data.search.SearchRemoteDataSource
import com.example.apod.data.search.SearchRepository
import com.example.apod.data.db.Media
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


enum class ApiStatus {NETWORK_ERROR, ERROR, DONE}

class HomeViewModel : ViewModel() {

    private val _media = MutableLiveData<Media>()/*.apply {
        //value = "https://apod.nasa.gov/apod/image/2203/PointReyesMilkyWayDanZafra1024.jpg"
        viewModelScope.launch {
            value = MediaRepository(MediaAPIImpl(),Dispatchers.IO).fetchMedia()
        }
    }*/
    val media: LiveData<Media> = _media

    private val _status = MutableLiveData<ApiStatus>()
    val status:LiveData<ApiStatus>
        get() = _status

    fun getMediaData(date:String="",context: Context){
        viewModelScope.launch {
            try {
                if (date.isEmpty()) {
                    _media.value = HomeRepository(
                        SearchRemoteDataSource(SearchAPIImpl(), Dispatchers.IO),
                        HomeLocalDataSource(context, Dispatchers.IO)
                    ).getTodaysData()
                    _status.value = ApiStatus.DONE
                } else {
                    if (isNetworkAvailable(context)){
                        _media.value = SearchRepository(
                            SearchRemoteDataSource(
                                SearchAPIImpl(),
                                Dispatchers.IO
                            )
                        ).fetchMedia(date)
                        _status.value = ApiStatus.DONE
                    }else{
                        _status.value = ApiStatus.NETWORK_ERROR
                        _media.value = HomeRepository(
                            SearchRemoteDataSource(SearchAPIImpl(), Dispatchers.IO),
                            HomeLocalDataSource(context, Dispatchers.IO)
                        ).getTodaysData()
                    }
                }
            }catch (e:Exception){
                e.printStackTrace()
                _status.value = ApiStatus.ERROR
            }
        }
    }

    fun addToFavorites(context:Context) {
        viewModelScope.launch {
            Log.d("TAG","Adding to fav ${media.value?.date}")
            FavoritesRepository(FavLocalDataSource(context),Dispatchers.IO).addToFavorites(media.value!!)
        }
    }

    private fun isNetworkAvailable(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
        val activeNetworkInfo = connectivityManager!!.activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isConnected
    }
}

