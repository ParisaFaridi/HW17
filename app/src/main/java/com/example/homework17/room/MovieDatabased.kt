package com.example.homework17.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.homework17.data.model.Movie

@Database(entities = [Movie::class], version = 1)
abstract class MovieDatabase : RoomDatabase(){
    abstract val movieDao:MovieDao
}