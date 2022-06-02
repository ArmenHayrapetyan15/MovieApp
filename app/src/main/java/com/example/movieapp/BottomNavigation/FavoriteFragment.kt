package com.example.movieapp.BottomNavigation

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieapp.RecyclerViewFavorite.DataList
import com.example.movieapp.RecyclerViewFavorite.FavoriteAdapter
import com.example.movieapp.databinding.FragmentFavoriteBinding

class FavoriteFragment : Fragment() {
    lateinit var binding: FragmentFavoriteBinding
    private lateinit var adapter: FavoriteAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoriteBinding.inflate(inflater)
        return binding.root
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = FavoriteAdapter(this@FavoriteFragment, DataList.favoriteList)
        adapter.notifyDataSetChanged()
        binding.RecyclerViewFavorite.adapter = adapter
        binding.RecyclerViewFavorite.layoutManager = LinearLayoutManager(this.context)
    }

}