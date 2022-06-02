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
import com.example.movieapp.validateEmail
import com.google.firebase.auth.FirebaseAuth

class RegistrationFragment : Fragment() {

    private lateinit var binding: FragmentRegistrationBinding
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
            if (!validateEmail(binding.etSEmailAddress.text.toString())){
                Toast.makeText(context, "Email Entered Incorrectly", Toast.LENGTH_SHORT).show()
            }else {
                signUpUser()
            }
        }

        binding.tvLogin.setOnClickListener {
            findNavController().navigate(R.id.action_registrationFragment_to_logInFragment)
        }

    }

    private fun createNewUser() {
        val firebaseUser = FirebaseAuth.getInstance().currentUser
        val hashMap = hashMapOf<String, Any>(
            "Email" to binding.etSEmailAddress.text.toString()
        )
        FirebaseUtils().fireStoreDatabase.collection("Users").document(firebaseUser!!.uid)
            .set(hashMap)
            .addOnSuccessListener {
                Toast.makeText(context, "Users are create", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener {
                Toast.makeText(context, "Error adding document $it", Toast.LENGTH_SHORT).show()
            }
    }

    private fun signUpUser() {
        val email = binding.etSEmailAddress.text.toString()
        val pass = binding.etSPassword.text.toString()
        val confirmPassword = binding.etSConfPassword.text.toString()

        if (email.isBlank() || pass.isBlank() || confirmPassword.isBlank()) {
            Toast.makeText(context, "Email and Password can't be blank", Toast.LENGTH_SHORT).show()
            return
        }
        
        if (pass.length < 6 || confirmPassword.length < 6){
            Toast.makeText(context, "The Password Must Be No Less Than 6 Letters", Toast.LENGTH_SHORT).show()
        }

        if (pass != confirmPassword) {
            Toast.makeText(context, "Password and Confirm Password do not match", Toast.LENGTH_SHORT)
                .show()
            return
        }

        auth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener {
            if (it.isSuccessful) {
                createNewUser()
                Toast.makeText(context, "Successfully Singed Up", Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.action_registrationFragment_to_generalFragment)
            } else {
                Toast.makeText(context, "Singed Up Failed!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}