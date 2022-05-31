package com.example.homework17.data.datasources

import androidx.lifecycle.LiveData
import com.example.homework17.data.model.Movie
import com.example.homework17.room.MovieDatabase

class LocalDataSource(private val movieDb: MovieDatabase) {

    suspend fun insert(movies: List<Movie>){
        for (i in movies)
            movieDb.movieDao.insert(i)
    }
    suspend fun getPopular():List<Movie> {
        return movieDb.movieDao.getPopular()
    }
    suspend fun getUpcoming(): List<Movie> {
        return movieDb.movieDao.getUpcoming()
    }
    suspend fun getDetail(id:Int):Movie{
        return movieDb.movieDao.getDetail(id)
    }
}