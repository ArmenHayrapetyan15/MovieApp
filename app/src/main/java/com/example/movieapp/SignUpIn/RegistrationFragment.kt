package com.example.movieapp.SignUpIn

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.movieapp.R
import com.example.movieapp.databinding.FragmentRegistrationBinding
import com.google.firebase.auth.FirebaseAuth


class RegistrationFragment : Fragment() {

    lateinit var binding: FragmentRegistrationBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        auth = FirebaseAuth.getInstance()
        binding = FragmentRegistrationBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.Registration.setOnClickListener {
            createNewUser()
//            findNavController().navigate(R.id.action_registrationFragment_to_generalFragment)
        }

        binding.tvLogin.setOnClickListener {
            findNavController().navigate(R.id.action_registrationFragment_to_logInFragment)
        }


    }

    private fun createNewUser() {
        auth.createUserWithEmailAndPassword(
            binding.etSEmailAddress.text.toString(),
            binding.etSPassword.text.toString()
        ).addOnCompleteListener { Task ->
            if (Task.isSuccessful) {
                val firebaseUser = FirebaseAuth.getInstance().currentUser
            }
        }
    }
}