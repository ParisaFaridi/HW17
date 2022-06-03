package com.example.homework17.data

import com.example.homework17.data.datasources.LocalDataSource
import com.example.homework17.data.datasources.RemoteDataSource
import com.example.homework17.data.model.Movie
import com.example.homework17.data.model.MovieList
import com.example.homework17.data.model.Trailer
import retrofit2.Response

class Repository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource) {

    suspend fun getPopular(hasInternet: Boolean): Response<MovieList> {
        return if (hasInternet) {
            val movies = remoteDataSource.getPopular()
            movies.body()?.let { localDataSource.insertPopular(it.results) }
            movies
        } else
            Response.success(MovieList(1, localDataSource.getPopular()))
    }
    suspend fun getUpcoming(hasInternet: Boolean): Response<MovieList> {
        return if (hasInternet) {
            val movies = remoteDataSource.getUpComings()
            movies.body()?.let { localDataSource.insertUpcoming(it.results) }
            movies
        } else
            Response.success(MovieList(1, localDataSource.getUpcoming()))
    }
    suspend fun getMovieDetail(movieId: Int): Response<Movie> {
        return remoteDataSource.getMovieDetail(movieId)
    }

    suspend fun getMovieDetailFromDb(movieId: Int): Movie {
        return localDataSource.getDetail(movieId)
    }

    suspend fun getTrailer(id: Int): Trailer {
        return remoteDataSource.getTrailer(id)
    }

    suspend fun search(query: String): List<Movie> {
        return remoteDataSource.search(query).results
    }
}