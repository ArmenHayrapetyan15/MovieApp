package com.example.movieapp.RecyclerViewHomePage

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.Bottomnavigation.HomeFragment
import com.example.movieapp.R
import com.example.movieapp.RecyclerViewFavorite.DataList
import com.example.movieapp.databinding.ItemMoviesBinding
import com.squareup.picasso.Picasso

class MoviesAdapter(
    var context: HomeFragment,
    private val movieList: MutableList<MovieItem>
) : RecyclerView.Adapter<MoviesAdapter.MovieViewHolder>() {
    var onItemClick:((MovieItem) -> Unit)? = null
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

        holder.bind(movieList[position])
        Picasso.get().load(movieList[position].image).into(holder.binding.MovieImage)
        holder.binding.MovieName.text = movieList[position].name

        holder.binding.OpenMovie.setOnClickListener {
            onItemClick?.invoke(movieList[position])
        }

        holder.binding.favoriteMovie.setOnClickListener {
            if (!movieList[position].isFavorite) {
                movieList[position].isFavorite = true
                holder.binding.favoriteMovie.setImageResource(R.drawable.ic_red_favorite_movie)
                DataList.favoriteList.add(
                    MovieItem(
                        movieList[position].id,
                        movieList[position].image,
                        movieList[position].name,
                        movieList[position].isFavorite,
                        movieList[position].trailerLink
                    )
                )

            } else {
                movieList[position].isFavorite = false
                holder.binding.favoriteMovie.setImageResource(R.drawable.ic_favorite_movie)

                for (i in DataList.favoriteList) {
                    if (movieList[position].id == i.id) {
                        DataList.favoriteList.remove(
                            MovieItem(movieList[position].id,
                                movieList[position].image,
                                movieList[position].name,
                                movieList[position].isFavorite,
                                movieList[position].trailerLink)
                        )
                    }
                }
            }
        }
    }

    override fun getItemCount() = movieList.size
}