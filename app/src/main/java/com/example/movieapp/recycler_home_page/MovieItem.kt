package com.example.movieapp.recycler_home_page

data class MovieItem(
    val id: Int,
    val image: String,
    val name: String,
    var isFavorite: Boolean = false,
    val trailerLink: String,
)
