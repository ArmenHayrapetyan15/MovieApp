package com.example.movieapp.bottom_navigation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieapp.R
import com.example.movieapp.favorite.Values
import com.example.movieapp.recycler_home_page.MovieItem
import com.example.movieapp.recycler_home_page.MoviesAdapter
import com.example.movieapp.databinding.FragmentHomeBinding
import com.example.movieapp.db.DataList
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var adapter: MoviesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentHomeBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initAdapter()
        showProgress()

        if (!DataList.isDataExist()) {
            Firebase.firestore.collection("Movies").get()
                .addOnSuccessListener { result ->
                    hideProgress()
                    val movies: MutableList<MovieItem> = ArrayList()
                    for (document in result) {
                        movies.add(
                            MovieItem(
                                document["id"].toString().toInt(),
                                document["imageUrl"].toString(),
                                document["name"].toString(),
                                document["isFavorite"] as Boolean,
                                document["trailerLink"].toString()
                            )
                        )
                        DataList.addData(movies)
                        adapter.updateItems(movies)
                    }

                }
                .addOnFailureListener { exception ->
                    hideProgress()
                    Log.e("TAG", "Error getting documents.", exception)
                }
        }else{
            adapter.updateItems(DataList.getData())
        }

        adapter.onItemClick = {
            Values.movieImage = it.image
            Values.movieName = it.name
            Values.movieTrailer = it.trailerLink
            findNavController().navigate(R.id.action_generalFragment_to_moviesFragment)

        }
    }

    private fun showProgress() {
        binding.progressBar.isVisible = true
    }

    private fun hideProgress() {
        binding.progressBar.isVisible = false
    }

    private fun initAdapter() {
        adapter = MoviesAdapter()
        binding.rvMovies.layoutManager = LinearLayoutManager(context)
        binding.rvMovies.adapter = adapter
    }
}