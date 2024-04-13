package com.example.apkjornada

import com.google.firebase.database.FirebaseDatabase

class FirebaseHelper {
    companion object
    fun getDatabase() = FirebaseDatabase.getInstance().reference
    fun validError(error: String) : Int{
        return when{
            error.contains("") -> {
                1

            }
            else -> {
                0

            }
        }
    }

}