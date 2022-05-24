package com.example.homework17.data.datasources

import com.example.homework17.data.model.Detail
import com.example.homework17.data.model.Movie
import com.example.homework17.data.model.Trailer
import com.example.homework17.network.ApiService

class RemoteDataSource(private val apiService: ApiService) {

    suspend fun getPopular(): List<Movie> {
            return apiService.getPopular().results
    }
    suspend fun getMovieDetail(movieId:Int): Detail {
        return apiService.getMovieDetail(movieId = movieId)
    }
    suspend fun getUpComings():List<Movie>{
        return apiService.getUpComings().results
    }
    suspend fun getTrailer(id:Int): Trailer{
        return apiService.getTrailer(id).results[0]
    }
}