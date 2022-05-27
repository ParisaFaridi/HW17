package com.example.homework17.data.datasources

import com.example.homework17.data.model.Movie
import com.example.homework17.data.model.Trailer
import com.example.homework17.network.ApiService
import java.lang.Exception

class RemoteDataSource(private val apiService: ApiService) {

    suspend fun getPopular(): List<Movie>? {
        return try {
            apiService.getPopular().results
        } catch (e: Exception) {
            null
        }
    }

    suspend fun getMovieDetail(movieId: Int): Movie? {
        return apiService.getMovieDetail(movieId = movieId)
    }

    suspend fun getUpComings(): List<Movie> {
        return apiService.getUpComings().results
    }

    suspend fun getTrailer(id: Int): Trailer {
        return apiService.getTrailer(id).results[0]
    }

    suspend fun search(query: String): List<Movie> {
        return apiService.search(query).results
    }
}