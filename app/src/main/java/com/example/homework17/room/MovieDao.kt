package com.example.homework17.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.homework17.data.model.Movie

@Dao
interface MovieDao {

    @Insert
    suspend fun insert(vararg movie:Movie)

    @Query("")
    suspend fun getPopular()

    @Query("")
    suspend fun getUpcoming()
}