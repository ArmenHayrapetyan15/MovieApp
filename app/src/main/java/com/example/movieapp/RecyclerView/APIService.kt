package com.example.movieapp.RecyclerView

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface APIService {
    @GET("shows")
    fun getMovieList(): Call<MutableList<MovieItem>>

    object NewService {
        val movieInstance: APIService

        init {
            val retrofitBulder = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://api.tvmaze.com/")
                .build()
                .create(APIService::class.java)
            movieInstance = retrofitBulder
        }
    }
}