package com.example.movieapp.RecyclerViewHomePage

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.MediaController
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.movieapp.R
import com.example.movieapp.RecyclerViewFavorite.Values
import com.example.movieapp.databinding.FragmentMoviesBinding
import com.squareup.picasso.Picasso

class MoviesFragment : Fragment() {

    lateinit var binding: FragmentMoviesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentMoviesBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tvMovieName.text = Values.movieName
        Picasso.get().load(Values.movieImage).into(binding.imgMovieImage)

        val mediaControllerTrailer = MediaController(context)
        binding.apply {
            mediaControllerTrailer.setAnchorView(vvMovieTrailer)
            val url = Uri.parse(Values.movieTrailer)
            vvMovieTrailer.setMediaController(mediaControllerTrailer)
            vvMovieTrailer.setVideoURI(url)
            vvMovieTrailer.requestFocus()
            vvMovieTrailer.start()
        }

        binding.imgPlayTrailer.setOnClickListener {
            binding.imgPlayTrailer.visibility = View.INVISIBLE
            binding.vvMovieTrailer.visibility = View.VISIBLE
        }

        binding.btnBack.setOnClickListener {
            findNavController().navigate(R.id.action_moviesFragment_to_generalFragment)
        }
    }
}