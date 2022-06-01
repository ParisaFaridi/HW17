package com.example.homework17.data.datasources

import com.example.homework17.data.model.Movie
import com.example.homework17.data.model.MovieList
import com.example.homework17.data.model.Trailer
import com.example.homework17.network.ApiService
import retrofit2.Response

class RemoteDataSource(private val apiService: ApiService) {

    suspend fun getPopular(): Response<MovieList> {
        return apiService.getPopular()
    }

    suspend fun getMovieDetail(movieId: Int): Movie {
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