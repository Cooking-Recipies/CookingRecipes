package com.example.cookingrecipes.ui.login

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.content.Intent
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import android.util.Patterns
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
    private lateinit var navView: NavigationView
    private val _loginResult = MutableLiveData<LoginResult>()
    val loginResult: LiveData<LoginResult> = _loginResult
    @SuppressLint("StaticFieldLeak")
    private val context = getApplication<Application>().applicationContext

    fun login(username: String, password: String) {
        // can be launched in a separate asynchronous job

        RetrofitClient.instance.loginUser(username,password).enqueue(object: retrofit2.Callback<LoginRequest> {
            override fun onResponse(call: Call<LoginRequest>, response: Response<LoginRequest>) {
                if (response.body() != null) {
                    SharedPreferenceManager.getInstance(context).saveUser(LoginRequest(response.body()?.token))
                    navView.findViewById<NavigationView>(R.id.nav_view).menu.clear()
                    navView.findViewById<NavigationView>(R.id.nav_view).inflateMenu(R.menu.activity_main_drawer_when_logged_in)
                    val intent = Intent(context,MainActivity::class.java)
                    context.startActivity(intent)
                }
            }

            override fun onFailure(call: Call<LoginRequest>, t: Throwable) {
                Toast.makeText(context,"something went wrong", Toast.LENGTH_LONG).show()
            }

        })
//        val result = loginRepository.login(username, password)
//
//        if (result is Result.Success) {
//            _loginResult.value =
//                LoginResult(success = LoggedInUserView(displayName = result.data.displayName))
//        } else {
//            _loginResult.value = LoginResult(error = R.string.login_failed)
//        }
    }

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