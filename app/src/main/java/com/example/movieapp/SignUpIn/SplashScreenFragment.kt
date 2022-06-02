package com.example.movieapp.SignUpIn

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.movieapp.R
import com.example.movieapp.RecyclerViewFavorite.DataList
import com.example.movieapp.RecyclerViewHomePage.MovieItem
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@SuppressLint("CustomSplashScreen")
class SplashScreenFragment : Fragment() {

    private lateinit var auth: FirebaseAuth

    private val activityScope = CoroutineScope(Dispatchers.Main)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        return inflater.inflate(R.layout.fragment_splash_screen, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        auth = FirebaseAuth.getInstance()
        activityScope.launch {
            delay(3000)
            if (auth.currentUser != null) {
                findNavController().navigate(
                    R.id.action_splashScreeFragment_to_generalFragment
                )
            } else {
                findNavController().navigate(
                    R.id.action_splashScreeFragment_to_loginRegistrationFragment
                )
            }
        }
        val db = Firebase.firestore
        Firebase.auth.currentUser?.let {
            db.collection("Movies")
                .get()
                .addOnSuccessListener { result ->
                    CoroutineScope(Dispatchers.Main).launch {
                        for (document in result) {
                            DataList.homeMovieList.add(
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

        }
    }
}


