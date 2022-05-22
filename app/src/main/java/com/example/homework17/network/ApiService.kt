package com.example.homework17.network

import com.example.homework17.data.model.MovieList
import retrofit2.http.GET
import retrofit2.http.Query

private const val API_KEY = "b8fb74a7f7ebe3f2402f6de80059d5a5"

interface ApiService {

    @GET("movie/popular")
    suspend fun getPopular(
        @Query("page") page:Int,
        @Query("api_key") apiKey:String = API_KEY
    ): MovieList

}