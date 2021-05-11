package com.example.cookingrecipes.ui.login

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.content.Intent
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import android.util.Patterns
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.AndroidViewModel
import com.example.cookingrecipes.Api.RetrofitClient
import com.example.cookingrecipes.MainActivity
import com.example.cookingrecipes.data.repositories.LoginRepository
import com.example.cookingrecipes.data.Result
import com.example.cookingrecipes.R
import com.example.cookingrecipes.data.model.LoginRequest
import com.example.cookingrecipes.data.storage.SharedPreferenceManager
import com.google.android.material.navigation.NavigationView
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class LoginViewModel(application: Application) : AndroidViewModel(application) {

    private val _loginForm = MutableLiveData<LoginFormState>()
    val loginFormState: LiveData<LoginFormState> = _loginForm
    private val _loginResult = MutableLiveData<LoginResult>()
    val loginResult: LiveData<LoginResult> = _loginResult
    private lateinit var navView: NavigationView

    @SuppressLint("StaticFieldLeak")
    private val context = getApplication<Application>().applicationContext



    fun loginDataChanged(username: String, password: String) {
        if (!isUserNameValid(username)) {
            _loginForm.value = LoginFormState(usernameError = R.string.invalid_username)
        } else if (!isPasswordValid(password)) {
            _loginForm.value = LoginFormState(passwordError = R.string.invalid_password)
        } else {
            _loginForm.value = LoginFormState(isDataValid = true)
        }
    }

    // A placeholder username validation check
    private fun isUserNameValid(username: String): Boolean {
        return if (username.contains("@")) {
            Patterns.EMAIL_ADDRESS.matcher(username).matches()
        } else {
            username.isNotBlank()
        }
    }

    // A placeholder password validation check
    private fun isPasswordValid(password: String): Boolean {
        return password.length > 5
    }
}