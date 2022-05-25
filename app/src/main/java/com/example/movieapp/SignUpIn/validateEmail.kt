package com.example.movieapp

import android.text.TextUtils
import android.util.Patterns

fun validateEmail(email: String): Boolean {
    return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches()
}