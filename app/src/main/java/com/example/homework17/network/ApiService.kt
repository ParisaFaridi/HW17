package com.example.homework17.network

import com.example.homework17.data.model.*
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

private const val API_KEY = "a22313b82c44d5f5f21b763ba898a2a7"
const val POSTER_PATH ="https://image.tmdb.org/t/p/w500"

interface ApiService {

    @GET("movie/popular")
    suspend fun getPopular(
        @Query("page")page :Int = 1,
        @Query("api_key") apiKey:String = API_KEY
    ): MovieList

    @GET("movie/{movie_id}")
    suspend fun getMovieDetail(
        @Path(value = "movie_id") movieId: Int,
        @Query("api_key") apiKey :String = API_KEY

    ): Movie

    @GET("movie/upcoming")
    suspend fun getUpComings(
        @Query("api_key")apiKey:String = API_KEY
    ): MovieList

    @GET("movie/{movie_id}/videos")
    suspend fun getTrailer(
        @Path(value = "movie_id") movieId: Int,
        @Query("api_key")apiKey:String = API_KEY
    ):Videos

    @GET("search/movie")
    suspend fun search(
        @Query("query")query:String,
        @Query("api_key")apiKey:String = API_KEY
    ):MovieList

}