package com.example.homework17.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Movie(
    @PrimaryKey
    val id: Int,
    val original_title: String,
    val overview: String,
    val poster_path: String?,
    val title: String,
    val vote_average: Double,
    val backdrop_path: String?,
    val isUpcoming:Boolean=false
)