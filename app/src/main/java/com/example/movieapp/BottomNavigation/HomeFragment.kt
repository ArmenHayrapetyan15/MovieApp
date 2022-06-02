package com.example.movieapp.BottomNavigation

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieapp.R
import com.example.movieapp.RecyclerViewFavorite.DataList.homeMovieList
import com.example.movieapp.RecyclerViewFavorite.Values
import com.example.movieapp.RecyclerViewHomePage.MoviesAdapter
import com.example.movieapp.databinding.FragmentHomeBinding

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

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = MoviesAdapter(this@HomeFragment, homeMovieList)
        adapter.notifyDataSetChanged()
        binding.RecyclerView.adapter = adapter
        binding.RecyclerView.layoutManager = LinearLayoutManager(this.context)

        adapter.onItemClick = {
            Values.movieImage = it.image
            Values.movieName = it.name
            Values.movieTrailer = it.trailerLink
            findNavController().navigate(R.id.action_generalFragment_to_moviesFragment)
        }
    }
}
