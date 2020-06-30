package com.example.batman_project.network

import com.example.batman_project.model.Detail
import com.example.batman_project.model.Movies
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface ApiService {
    @GET(".")
    fun getAllMovies(@QueryMap map: Map<String, String>): Call<Movies>

    @GET(".")
    fun getDetail(@QueryMap map: Map<String, String>): Call<Detail>
}