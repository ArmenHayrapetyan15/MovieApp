package com.example.movieapp.RecyclerView

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.Bottomnavigation.HomeFragment
import com.example.movieapp.R
import com.example.movieapp.databinding.ItemMoviesBinding
import com.squareup.picasso.Picasso

class MoviesAdapter(
    var context: HomeFragment,
    private val movieList: MutableList<MovieItem>
) : RecyclerView.Adapter<MoviesAdapter.MovieViewHolder>() {


    inner class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var isClicked = false
        var binding = ItemMoviesBinding.bind(itemView)

        fun bind() = with(binding) {
            FavoriteMovie.setOnClickListener {
                if (!isClicked) {
                    isClicked = true
                    FavoriteMovie.setImageResource(R.drawable.ic_favorite_movie)
                } else {
                    isClicked = false
                    FavoriteMovie.setImageResource(R.drawable.ic_red_favorite_movie)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MovieViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_movies, parent, false)
    )

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        Picasso.get().load(movieList[position].image.medium).into(holder.binding.MovieImage)
        holder.binding.MovieName.text = movieList[position].name
        holder.bind()
    }

    override fun getItemCount() = movieList.size

}