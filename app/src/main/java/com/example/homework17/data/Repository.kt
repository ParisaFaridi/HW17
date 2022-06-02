package com.example.homework17.data

import com.example.homework17.data.datasources.LocalDataSource
import com.example.homework17.data.datasources.RemoteDataSource
import com.example.homework17.data.model.Movie
import com.example.homework17.data.model.MovieList
import com.example.homework17.data.model.Trailer
import retrofit2.Response
import java.lang.Exception

class Repository(private val remoteDataSource: RemoteDataSource,private val localDataSource: LocalDataSource){

    suspend fun getPopular(): Response<MovieList> {
        val movies = remoteDataSource.getPopular()
        movies.body()?.let { localDataSource.insertPopular(it.results) }
        return movies
    }
    suspend fun getPopularFromDb():List<Movie>{
        return localDataSource.getPopular()
    }
    suspend fun getMovieDetail(movieId:Int): Movie {
        return try {
            remoteDataSource.getMovieDetail(movieId)
        }catch (e :Exception){
            localDataSource.getDetail(movieId)
        }
    }
    suspend fun getUpComings():List<Movie>{
        return try{
            val movies =remoteDataSource.getUpComings().results
            localDataSource.insertUpcoming(movies)
            movies
        }catch (e:Exception){
            localDataSource.getUpcoming()
        }
    }
    suspend fun getTrailer(id:Int): Trailer {
        return remoteDataSource.getTrailer(id)
    }
    suspend fun search(query:String): List<Movie>{
        return remoteDataSource.search(query).results
    }
}