package com.example.movieapp.Bottomnavigation

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieapp.RecyclerView.APIService
import com.example.movieapp.RecyclerView.MovieItem
import com.example.movieapp.RecyclerView.MoviesAdapter
import com.example.movieapp.databinding.FragmentHomeBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment() {

    lateinit var binding: FragmentHomeBinding
    lateinit var adapter: MoviesAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.RecyclerView.layoutManager = LinearLayoutManager(this.context)
        getMovieData()

    }

    private fun getMovieData() {
        val retrofit = APIService.NewService.movieInstance.getMovieList()
        retrofit.enqueue(object : Callback<MutableList<MovieItem>> {
            @SuppressLint("NotifyDataSetChanged")
            override fun onResponse(
                call: Call<MutableList<MovieItem>?>,
                response: Response<MutableList<MovieItem>?>
            ) {
                val responseBody = response.body()

                adapter =
                    MoviesAdapter(this@HomeFragment, responseBody as MutableList<MovieItem>)
                adapter.notifyDataSetChanged()
                binding.RecyclerView.adapter = adapter
            }

            override fun onFailure(call: Call<MutableList<MovieItem>?>, t: Throwable) {
                Log.d("MyLog", "ofFailure ${t.message}")
            }
        })
    }
}