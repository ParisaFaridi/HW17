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
        return try {
            apiService.getMovieDetail(movieId = movieId)
        } catch (e: Exception) {
            null
        }
    }

    suspend fun getUpComings(): List<Movie>? {
        return try {
            apiService.getUpComings().results
        } catch (e: Exception) {
            null
        }
    }

    suspend fun getTrailer(id: Int): Trailer? {
        return try {
            apiService.getTrailer(id).results[0]
        } catch (e: Exception) {
            null
        }
    }

    suspend fun search(query: String): List<Movie>? {
        return try {
            apiService.search(query).results
        } catch (e: Exception) {
            null
        }
    }
}