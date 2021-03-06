package com.example.movieapp.sign_up

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.movieapp.R
import com.google.firebase.auth.FirebaseAuth
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
    }
}