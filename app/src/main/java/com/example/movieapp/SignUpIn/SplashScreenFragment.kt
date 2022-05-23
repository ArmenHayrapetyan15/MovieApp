package com.example.movieapp.SignUpIn

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.movieapp.R
import kotlinx.coroutines.*

class SplashScreenFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_splash_screen, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        GlobalScope.launch(Dispatchers.Default) {
            timer()
        }

    }

    private suspend fun timer() {
        delay(3000)
        withContext(Dispatchers.Main) {
            findNavController().navigate(R.id.action_splashScreeFragment_to_loginRegistrationFragment)
        }
    }
}


