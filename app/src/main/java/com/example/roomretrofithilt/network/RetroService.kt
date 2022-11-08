package com.example.roomretrofithilt.network

import com.example.roomretrofithilt.model.RepositoriesList
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RetroService {
    @GET("repositories")
    fun getDataFromApi(@Query("q")query:String) : Call<RepositoriesList>
}