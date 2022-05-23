package com.example.movieapp.SignUpIn

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.movieapp.R
import com.example.movieapp.databinding.FragmentLoginRegistrationBinding

class LoginRegistrationFragment : Fragment() {

    lateinit var binding: FragmentLoginRegistrationBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginRegistrationBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.Login.setOnClickListener {
            findNavController().navigate(R.id.action_loginRegistrationFragment_to_logInFragment)
        }

        binding.registration.setOnClickListener {
            findNavController().navigate(R.id.action_loginRegistrationFragment_to_registrationFragment)
        }
    }
}