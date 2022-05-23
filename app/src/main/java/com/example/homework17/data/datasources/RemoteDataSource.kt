package com.example.homework17.data.datasources

import com.example.homework17.data.model.Detail
import com.example.homework17.data.model.Movie
import com.example.homework17.network.ApiService
import retrofit2.http.Path

class RemoteDataSource(private val apiService: ApiService) {

    suspend fun getPopular(): List<Movie> {
            return apiService.getPopular().results
    }
    suspend fun getMovieDetail(movieId:Int): Detail {
        return apiService.getMovieDetail(movieId = movieId)
    }
}