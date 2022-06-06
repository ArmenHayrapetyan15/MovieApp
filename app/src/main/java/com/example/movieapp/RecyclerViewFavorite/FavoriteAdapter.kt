package com.example.movieapp.RecyclerViewFavorite

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.BottomNavigation.FavoriteFragment
import com.example.movieapp.R
import com.example.movieapp.RecyclerViewHomePage.MovieItem
import com.example.movieapp.databinding.ItemMoviesBinding
import com.squareup.picasso.Picasso

class FavoriteAdapter(
    var context: FavoriteFragment,
    private val favoriteMovieList: MutableList<MovieItem>,
) : RecyclerView.Adapter<FavoriteAdapter.FavoriteMovieHolder>() {
    private var onItemClick: ((MovieItem) -> Unit)? = null

    inner class FavoriteMovieHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var binding = ItemMoviesBinding.bind(itemView)
        fun bind() = with(binding) {
            if (favoriteMovieList[position].isFavorite) {
                favoriteMovie.setImageResource(R.drawable.ic_red_favorite_movie)
            } else {
                favoriteMovie.setImageResource(R.drawable.ic_favorite_movie)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = FavoriteMovieHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_movies, parent, false)
    )

    @SuppressLint("NotifyDataSetChanged")
    override fun onBindViewHolder(holder: FavoriteMovieHolder, position: Int) {

        holder.binding.OpenMovie.setOnClickListener {
            onItemClick?.invoke(favoriteMovieList[position])
        }

        holder.bind()
        Picasso.get().load(favoriteMovieList[position].image).into(holder.binding.MovieImage)
        holder.binding.MovieName.text = favoriteMovieList[position].name

        holder.binding.favoriteMovie.setOnClickListener {
            try {
                if (favoriteMovieList[position].isFavorite) {
                    favoriteMovieList[position].isFavorite = false
                    holder.binding.favoriteMovie.setImageResource(R.drawable.ic_favorite_movie)
                    for (it in DataList.homeMovieList) {
                        for (it1 in favoriteMovieList) {
                            if (it.name == it1.name) {
                                it.isFavorite = false
                                DataList.favoriteList.remove(favoriteMovieList[position])
                                notifyDataSetChanged()
                            }
                        }
                    }
                }
            } catch (e: Exception) {
                Log.e(TAG, "onBindViewHolder:Removed doesn't work")
            }
        }
    }

    override fun getItemCount() = favoriteMovieList.size
}