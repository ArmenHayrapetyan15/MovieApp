package com.example.movieapp.db

import com.example.movieapp.recycler_home_page.MovieItem

object DataList {
    var favoriteList = mutableListOf<MovieItem>()
    private var homeMovieList = mutableListOf<MovieItem>()

    fun addData(list: MutableList<MovieItem>) {
        homeMovieList.clear()
        homeMovieList.addAll(list)
    }

    fun getData() = homeMovieList

    fun isDataExist(): Boolean = !homeMovieList.isEmpty()
}