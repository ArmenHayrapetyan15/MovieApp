package com.example.movieapp.SignUpIn

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.movieapp.R
import com.example.movieapp.databinding.FragmentLogInBinding
import com.google.firebase.auth.FirebaseAuth

class LogInFragment : Fragment() {

    lateinit var binding: FragmentLogInBinding
    lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLogInBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.Login.setOnClickListener {
            login()
        }

        binding.tvSignUp.setOnClickListener {
            findNavController().navigate(R.id.action_logInFragment_to_registrationFragment)
        }

        auth = FirebaseAuth.getInstance()
    }

    private fun login() {
        val email = binding.etEmailAddress.text.toString()
        val pass = binding.etPassword.text.toString()
        auth.signInWithEmailAndPassword(email, pass).addOnCompleteListener {
            if (it.isSuccessful) {
                Toast.makeText(context, "Successfully LoggedIn", Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.action_logInFragment_to_generalFragment)
            } else {
                Toast.makeText(context, "Log In failed ", Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.action_logInFragment_to_registrationFragment)
            }
        }
    }
}