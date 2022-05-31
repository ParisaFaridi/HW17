package com.example.homework17.data.datasources

import androidx.lifecycle.LiveData
import com.example.homework17.data.model.Movie
import com.example.homework17.room.MovieDatabase

class LocalDataSource(private val movieDb: MovieDatabase) {

    suspend fun insertPopular(movies: List<Movie>){
        for (i in movies)
            movieDb.movieDao.insert(i)
    }
    suspend fun insertUpcoming(movies: List<Movie>){
        for (i in movies){
            movieDb.movieDao.insert(Movie(i.id,i.original_title,i.overview,i.poster_path,i.title
            ,i.vote_average,i.backdrop_path,true))
        }
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