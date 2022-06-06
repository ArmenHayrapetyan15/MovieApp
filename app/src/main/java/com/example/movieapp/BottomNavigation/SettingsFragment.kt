package com.example.movieapp.BottomNavigation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.movieapp.R
import com.example.movieapp.RecyclerViewFavorite.DataList
import com.example.movieapp.SignUpIn.FirebaseUtils
import com.example.movieapp.databinding.FragmentSettingsBinding
import com.google.firebase.auth.FirebaseAuth

class SettingsFragment : Fragment() {
    lateinit var binding: FragmentSettingsBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var email: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentSettingsBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.SignOut.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            findNavController().navigate(R.id.action_generalFragment_to_loginRegistrationFragment)
            DataList.favoriteList = mutableListOf()
        }

        auth = FirebaseAuth.getInstance()


        FirebaseUtils().fireStoreDatabase.collection("Users")
            .document(auth.currentUser?.uid!!)
            .get()
            .addOnSuccessListener { querySnapshot ->
                email = querySnapshot.data?.get("Email").toString()
                binding.UserEmail.text = email
            }
            .addOnFailureListener { exception ->
                Log.e("TAG", "Error getting documents $exception")
            }
    }
}