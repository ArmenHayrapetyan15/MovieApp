package com.example.movieapp.RecyclerViewHomePage

import android.annotation.SuppressLint
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class APIService {

    lateinit var adapter: MoviesAdapter

    fun enqueue(fragment: Fragment, view : RecyclerView) {
        object : Callback<MutableList<MovieItem>> {
            @SuppressLint("NotifyDataSetChanged")
            override fun onResponse(
                call: Call<MutableList<MovieItem>?>,
                response: Response<MutableList<MovieItem>?>
            ) {
//                adapter = MoviesAdapter(fragment as HomeFragment)
//                adapter.notifyDataSetChanged()
//                view.adapter = adapter
            }

            override fun onFailure(call: Call<MutableList<MovieItem>?>, t: Throwable) {
                Log.d("MyLog", "ofFailure ${t.message}")
            }
        }
    }
}