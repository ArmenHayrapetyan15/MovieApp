package com.example.movieapp.Network

import com.example.movieapp.MovieModelItem
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface APIService {
    @GET("most-popular?page=:page")
    fun getMovieList(): Call<MutableList<MovieModelItem>>

    object NewService {
        val movieInstance: APIService

        init {
            val retrofitBulder = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://www.episodate.com/api/")
                .build()
            movieInstance = retrofitBulder.create(APIService::class.java)
        }
    }
}