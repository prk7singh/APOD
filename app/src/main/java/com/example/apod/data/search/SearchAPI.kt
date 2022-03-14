package com.example.apod.data.search

import com.example.apod.data.db.Media

interface SearchAPI {
    fun fetchMedia(date:String=""): Media?
}