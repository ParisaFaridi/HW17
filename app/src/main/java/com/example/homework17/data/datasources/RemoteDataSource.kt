package com.example.homework17.data.datasources

import com.example.homework17.data.model.Movie
import com.example.homework17.data.model.MovieList
import com.example.homework17.network.ApiService
import java.lang.Exception

class RemoteDataSource(private val apiService: ApiService) {

    suspend fun getPopular(): List<Movie> {
            return apiService.getPopular().results

    }
}