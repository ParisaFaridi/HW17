package com.example.homework17.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.homework17.data.model.Movie

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(movie: Movie)

    @Query("SELECT * FROM Movie WHERE isUpcoming = 0")
    suspend fun getPopular():List<Movie>

    @Query("SELECT * FROM Movie WHERE isUpcoming = 1")
    suspend fun getUpcoming():List<Movie>

    @Query("SELECT * FROM MOVIE WHERE id = :id LIMIT 1")
    suspend fun getDetail(id:Int):Movie

    @Query("SELECT * FROM MOVIE WHERE title LIKE :searchQuery")
    suspend fun search(searchQuery:String):List<Movie>
}