package com.example.movieapp.RecyclerViewFavorite

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.Bottomnavigation.FavoriteFragment
import com.example.movieapp.R
import com.example.movieapp.RecyclerViewHomePage.MovieItem
import com.example.movieapp.databinding.ItemMoviesBinding
import com.squareup.picasso.Picasso

class FavoriteAdapter(
    var context: FavoriteFragment,
    private val favoriteMovieList: MutableList<MovieItem>,
) : RecyclerView.Adapter<FavoriteAdapter.FavoriteMovieHolder>() {
    private var onItemClick:((MovieItem) -> Unit)? = null
    inner class FavoriteMovieHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var binding = ItemMoviesBinding.bind(itemView)
        fun bind() = with(binding) {
            if (favoriteMovieList[position].isFavorite) {
                favoriteMovie.setImageResource(R.drawable.ic_red_favorite_movie)
            }else{
                favoriteMovie.setImageResource(R.drawable.ic_favorite_movie)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = FavoriteMovieHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_movies, parent, false)
    )

    override fun onBindViewHolder(holder: FavoriteMovieHolder, position: Int) {

        holder.binding.OpenMovie.setOnClickListener {
            onItemClick?.invoke(favoriteMovieList[position])
        }

        holder.bind()
        Picasso.get().load(favoriteMovieList[position].image).into(holder.binding.MovieImage)
        holder.binding.MovieName.text = favoriteMovieList[position].name

        holder.binding.favoriteMovie.setOnClickListener {
            if (!favoriteMovieList[position].isFavorite) {
                favoriteMovieList[position].isFavorite = true
                holder.binding.favoriteMovie.setImageResource(R.drawable.ic_red_favorite_movie)
            } else {
                favoriteMovieList[position].isFavorite = false
                holder.binding.favoriteMovie.setImageResource(R.drawable.ic_favorite_movie)
                DataList.homeMovieList[position].isFavorite = favoriteMovieList[position].isFavorite
                DataList.favoriteList.remove(favoriteMovieList[position])
            }
        }
    }

    override fun getItemCount() = favoriteMovieList.size
}