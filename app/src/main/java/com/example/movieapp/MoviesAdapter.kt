package com.example.movieapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.Bottomnavigation.HomeFragment
import com.example.movieapp.databinding.ItemMoviesBinding
import com.squareup.picasso.Picasso

class MoviesAdapter(
    var context: HomeFragment,
    private val movieList: MutableList<MovieModelItem>
) : RecyclerView.Adapter<MoviesAdapter.MovieViewHolder>() {


    inner class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var isClicked = false
        var binding = ItemMoviesBinding.bind(itemView)

        fun bind(movieModelItem: MovieModelItem) = with(binding) {
            MovieName.text = movieModelItem.name
            Picasso.get().load(movieModelItem.imageUrl).into(MovieImage)

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
        holder.bind(movieList[position])
    }

    override fun getItemCount() = movieList.size

}