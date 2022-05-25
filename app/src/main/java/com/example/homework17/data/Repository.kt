package com.example.homework17.data

import com.example.homework17.data.datasources.LocalDataSource
import com.example.homework17.data.datasources.RemoteDataSource
import com.example.homework17.data.model.Movie
import com.example.homework17.data.model.Trailer

class Repository(val remoteDataSource: RemoteDataSource,localDataSource: LocalDataSource){

    suspend fun getPopular(): List<Movie> {
        return remoteDataSource.getPopular()
    }
    suspend fun getMovieDetail(movieId:Int): Movie {
        return remoteDataSource.getMovieDetail(movieId)
    }
    suspend fun getUpComings():List<Movie>{
        return remoteDataSource.getUpComings()
    }
    suspend fun getTrailer(id:Int): Trailer {
        return remoteDataSource.getTrailer(id)
    }
    suspend fun search(query:String): List<Movie>{
        return remoteDataSource.search(query)
    }
}