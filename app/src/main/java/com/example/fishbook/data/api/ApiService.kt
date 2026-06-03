package com.example.fishbook.data.api

import com.example.fishbook.data.model.CatFact
import com.example.fishbook.data.model.PhotoResponse
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("fact")
    fun getCatFact(): Call<CatFact>

    @GET("v2/list")
    fun getPhotos(): Call<List<PhotoResponse>>
}
