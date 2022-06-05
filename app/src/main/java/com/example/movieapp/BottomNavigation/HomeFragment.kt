package com.example.movieapp.BottomNavigation

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieapp.R
import com.example.movieapp.RecyclerViewFavorite.DataList
import com.example.movieapp.RecyclerViewFavorite.DataList.homeMovieList
import com.example.movieapp.RecyclerViewFavorite.Values
import com.example.movieapp.RecyclerViewHomePage.MovieItem
import com.example.movieapp.RecyclerViewHomePage.MoviesAdapter
import com.example.movieapp.databinding.FragmentHomeBinding
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

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

        CoroutineScope(Dispatchers.Main).launch {
            if (!DataList.bool) {
                binding.progressBar.visibility = View.VISIBLE
                DataList.bool = true
                val db = Firebase.firestore
                Firebase.auth.currentUser?.let {
                    db.collection("Movies")
                        .get()
                        .addOnSuccessListener { result ->
                            CoroutineScope(Dispatchers.Main).launch {
                                for (document in result) {
                                    homeMovieList.add(
                                        MovieItem(
                                            document["id"].toString().toInt(),
                                            document["imageUrl"].toString(),
                                            document["name"].toString(),
                                            document["isFavorite"] as Boolean,
                                            document["trailerLink"].toString()
                                        ))
                                }
                            }
                        }
                        .addOnFailureListener { exception ->
                            Log.w("TAG", "Error getting documents.", exception)
                        }
                    adapter = MoviesAdapter(this@HomeFragment, homeMovieList)
                    adapter.notifyDataSetChanged()
                    binding.RecyclerView.adapter = adapter
                    binding.RecyclerView.layoutManager = LinearLayoutManager(context)
                    delay(3000)
                    binding.progressBar.visibility = View.GONE

                }
            } else {
                binding.progressBar.visibility = View.GONE
                adapter = MoviesAdapter(this@HomeFragment, homeMovieList)
                adapter.notifyDataSetChanged()
                binding.RecyclerView.adapter = adapter
                binding.RecyclerView.layoutManager = LinearLayoutManager(context)
                delay(3000)
                binding.progressBar.visibility = View.GONE
            }

            adapter.onItemClick = {
                Values.movieImage = it.image
                Values.movieName = it.name
                Values.movieTrailer = it.trailerLink
                findNavController().navigate(R.id.action_generalFragment_to_moviesFragment)
            }
        }
    }
}
