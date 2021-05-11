package com.example.cookingrecipes.data.storage

import android.content.Context
import com.example.cookingrecipes.data.model.LoginRequest
import kotlin.math.ln

class SharedPreferenceManager private constructor(private val context:Context){

    companion object{
        private var instance: SharedPreferenceManager? = null
        @Synchronized
        fun getInstance(context: Context): SharedPreferenceManager{
            if(instance == null) instance = SharedPreferenceManager(context)
            return instance as SharedPreferenceManager
        }
    }

    val isLoggedIn: String?
    get(){
        val sharedPreferences = context.getSharedPreferences("shared_prefferences", Context.MODE_PRIVATE)
        return sharedPreferences.getString("token","")
    }

    val user: LoginRequest
    get(){
        val sharedPreferences = context.getSharedPreferences("shared_prefferences", Context.MODE_PRIVATE)
        return LoginRequest(sharedPreferences.getString("token",""),sharedPreferences.getString("email",""))
    }

    fun saveUser(user: LoginRequest) {
        val sharedPreferences = context.getSharedPreferences("shared_prefferences", Context.MODE_PRIVATE)
        sharedPreferences
            .edit()
            .putString("token", user.token)
            .apply()
    }

    fun clearUser(){
        val sharedPreferences = context.getSharedPreferences("shared_prefferences", Context.MODE_PRIVATE)
        sharedPreferences.edit()
            .clear()
            .apply()
    }
}