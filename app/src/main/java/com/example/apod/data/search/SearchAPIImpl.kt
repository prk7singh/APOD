package com.example.apod.data.search

import android.util.Log
import com.example.apod.data.db.Media
import com.google.gson.Gson
import okhttp3.HttpUrl.Companion.toHttpUrlOrNull
import okhttp3.OkHttpClient
import okhttp3.Request

class SearchAPIImpl: SearchAPI {
    private val client = OkHttpClient()

    companion object{
        private val TAG = this::class.java.name
        private const val baseUrl = "https://api.nasa.gov/planetary/apod"
        private const val API_KEY = "VDWTKPTjfUdH2UDDg2g5pBHSlTE5V6Ynw47IVvD4"
        private const val API_QUERY = "api_key"
        private const val DATE_QUERY = "date"
    }

    override fun fetchMedia(date: String): Media? {
        val httpUrl = baseUrl.toHttpUrlOrNull() ?: throw IllegalStateException("baseUrl is invalid.")

        var urlBuilder = httpUrl.newBuilder()
        urlBuilder = urlBuilder.addQueryParameter(API_QUERY, API_KEY)
        if (date.isNotEmpty())
            urlBuilder = urlBuilder.addQueryParameter(DATE_QUERY,date)

        val url = urlBuilder.build()
        val request: Request = Request.Builder()
            .url(url)
            .build()

        val response = client.newCall(request).execute()
        return if (response.isSuccessful){
            val responseBody = response.body?.string()
            Log.d(TAG,"response: $responseBody")
            val media = Gson().fromJson(responseBody, Media::class.java)
            media
        }else{
            null
        }
    }
}