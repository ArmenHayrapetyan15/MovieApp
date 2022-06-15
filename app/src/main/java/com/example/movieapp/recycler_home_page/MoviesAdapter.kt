package com.example.movieapp.recycler_home_page

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.R
import com.example.movieapp.db.DataList
import com.example.movieapp.databinding.ItemMoviesBinding
import com.squareup.picasso.Picasso

class MoviesAdapter : RecyclerView.Adapter<MoviesAdapter.MovieViewHolder>() {

    var onItemClick: ((MovieItem) -> Unit)? = null
    private val movieList: MutableList<MovieItem> = ArrayList()

    inner class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var binding = ItemMoviesBinding.bind(itemView)

        fun bind(movieItem: MovieItem) = with(binding) {
            if (!movieItem.isFavorite) {
                favoriteMovie.setImageResource(R.drawable.ic_favorite_movie)
            } else {
                favoriteMovie.setImageResource(R.drawable.ic_red_favorite_movie)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MovieViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_movies, parent, false)
    )

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movieItem = movieList[position]
        holder.bind(movieItem)

        Picasso.get().load(movieItem.image).into(holder.binding.movieImage)
        holder.binding.movieNameTv.text = movieItem.name

        holder.binding.openMovieDetailsBtn.setOnClickListener {
            onItemClick?.invoke(movieList[position])
        }

        holder.binding.favoriteMovie.setOnClickListener {
            if (!movieItem.isFavorite) {
                movieItem.isFavorite = true
                holder.binding.favoriteMovie.setImageResource(R.drawable.ic_red_favorite_movie)
                DataList.favoriteList.add(movieItem)
            }
        }
    }

    override fun getItemCount() = movieList.size

    fun updateItems(list: MutableList<MovieItem>) {
        movieList.clear()
        movieList.addAll(list)
        notifyDataSetChanged()
    }
}