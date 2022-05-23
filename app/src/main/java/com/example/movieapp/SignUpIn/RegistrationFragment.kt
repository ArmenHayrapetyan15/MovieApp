package com.example.movieapp.SignUpIn

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.movieapp.R
import com.example.movieapp.databinding.FragmentRegistrationBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class RegistrationFragment : Fragment() {

    lateinit var binding: FragmentRegistrationBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRegistrationBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.Registration.setOnClickListener {
            findNavController().navigate(R.id.action_registrationFragment_to_generalFragment)
        }

        binding.tvLogin.setOnClickListener {
            findNavController().navigate(R.id.action_registrationFragment_to_logInFragment)
        }

        auth = Firebase.auth
    }

    private fun signUpUser() {
        val email = binding.etSEmailAddress.text.toString()
        val pass = binding.etSPassword.text.toString()
        val confirmPassword = binding.etSConfPassword.text.toString()

        if (email.isBlank() || email.isEmpty() || pass.isBlank() || pass.isEmpty() || confirmPassword.isBlank() || confirmPassword.isEmpty()) {
            Toast.makeText(context, "Email and Password can't be empty", Toast.LENGTH_SHORT).show()
            return
        }

        if (pass != confirmPassword) {
            Toast.makeText(
                context,
                "Password and Confirm Password do not match",
                Toast.LENGTH_SHORT
            )
                .show()
            return
        }
        auth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(requireActivity()) {
            if (it.isSuccessful) {
                Toast.makeText(context, "Successfully Singed Up", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, "Singed Up Failed!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun createNewUser() {
        val firebaseUser = FirebaseAuth.getInstance().currentUser
        val db = Firebase.firestore

        val userDate = hashMapOf(
            "Email" to binding.etSEmailAddress.text.toString(),
        )

        db.collection("users").document(firebaseUser!!.uid)
            .set(userDate)
            .addOnSuccessListener { documentReference ->
//                Timber.tag(ContentValues.TAG).d("DocumentSnapshot written with ID: ")
            }
            .addOnFailureListener { e ->
//                Timber.tag(ContentValues.TAG).w(e, "Error adding document")
            }
    }
}