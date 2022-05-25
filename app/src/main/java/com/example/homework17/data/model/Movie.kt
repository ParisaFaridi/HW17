package com.example.homework17.data.model

data class Movie(
    val id: Int,
    val original_title: String,
    val overview: String,
    val poster_path: String?,
    val title: String,
    val vote_average: Double,
)