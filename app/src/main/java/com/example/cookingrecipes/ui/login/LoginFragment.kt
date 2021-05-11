package com.example.cookingrecipes.ui.login

import android.content.Intent
import android.media.FaceDetector
import android.os.Bundle
import android.text.Editable
import android.text.Layout
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.cookingrecipes.Api.RetrofitClient
import com.example.cookingrecipes.MainActivity
import com.example.cookingrecipes.R
import com.example.cookingrecipes.RegisterActivity
import com.example.cookingrecipes.data.model.LoginRequest
import com.example.cookingrecipes.data.storage.SharedPreferenceManager
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_login.*
import retrofit2.Call
import retrofit2.Response
import java.lang.Exception
import java.util.*


class LoginFragment : Fragment() {

    private lateinit var loginViewModel: LoginViewModel
    lateinit var callbackManager: CallbackManager

    private val EMAIL = "email"
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loginViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        val usernameEditText = view.findViewById<EditText>(R.id.username)
        val passwordEditText = view.findViewById<EditText>(R.id.password)
        val loginButton = view.findViewById<Button>(R.id.login)
        val loadingProgressBar = view.findViewById<ProgressBar>(R.id.loading)
        val registerButton = view.findViewById<Button>(R.id.register)
        val facebookButton = view.findViewById<com.facebook.login.widget.LoginButton>(R.id.login_facebook)

        facebookButton.setReadPermissions(Arrays.asList(EMAIL))

        callbackManager = CallbackManager.Factory.create()
        facebookButton.setOnClickListener {
            LoginManager.getInstance()
                .logInWithReadPermissions(this, Arrays.asList("public_profile", "email"))
            LoginManager.getInstance().registerCallback(callbackManager,
                object : FacebookCallback<com.facebook.login.LoginResult> {
                    override fun onSuccess(loginResult: LoginResult) {

                        SharedPreferenceManager.getInstance(requireContext())
                            .saveUser(LoginRequest(loginResult.accessToken.toString()))

//                        navView.menu.clear()
//                        navView.inflateMenu(R.menu.activity_main_drawer_when_logged_in)
                        val intent = Intent(context, MainActivity::class.java)
                        context?.startActivity(intent)
                    }

                    override fun onCancel() {
                        Log.d("MainActivity", "Facebook onCancel.")

                    }

                    override fun onError(error: FacebookException) {
                        Log.d("MainActivity", "Facebook onError.")

                    }
                })
        }

        loginViewModel.loginFormState.observe(viewLifecycleOwner,
            Observer { loginFormState ->
                if (loginFormState == null) {
                    return@Observer
                }
                loginButton.isEnabled = loginFormState.isDataValid
                loginFormState.usernameError?.let {
                    usernameEditText.error = getString(it)
                }
                loginFormState.passwordError?.let {
                    passwordEditText.error = getString(it)
                }
            })

        loginViewModel.loginResult.observe(viewLifecycleOwner,
            Observer { loginResult ->
                loginResult ?: return@Observer
                loadingProgressBar.visibility = View.GONE
                loginResult.error?.let {
                    showLoginFailed(it)
                }
                loginResult.success?.let {
                    updateUiWithUser(it)
                }
            })

        val afterTextChangedListener = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable) {
                loginViewModel.loginDataChanged(
                    usernameEditText.text.toString(),
                    passwordEditText.text.toString()
                )
            }
        }
        usernameEditText.addTextChangedListener(afterTextChangedListener)
        passwordEditText.addTextChangedListener(afterTextChangedListener)
        passwordEditText.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                login(
                    usernameEditText.text.toString(),
                    passwordEditText.text.toString()
                )
            }
            false
        }

        loginButton.setOnClickListener {
            loadingProgressBar.visibility = View.VISIBLE
            login(
                usernameEditText.text.toString(),
                passwordEditText.text.toString()
            )
        }

        registerButton.isEnabled = true
        registerButton.setOnClickListener {
            val intent = Intent(activity, RegisterActivity::class.java)
            activity?.startActivity(intent)
        }
    }

    fun login(username: String, password: String) {
        // can be launched in a separate asynchronous job
        RetrofitClient.instance.loginUser(username,password).enqueue(object: retrofit2.Callback<LoginRequest> {
            override fun onResponse(call: Call<LoginRequest>, response: Response<LoginRequest>) {
                if (response.body() != null) {
                    SharedPreferenceManager.getInstance(requireContext()).saveUser(LoginRequest(response.body()?.token))

                    val intent = Intent(context,MainActivity::class.java)
                    context?.startActivity(intent)
                }
            }

            override fun onFailure(call: Call<LoginRequest>, t: Throwable) {
                println(t.message)
            }

        })
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        callbackManager.onActivityResult(requestCode,resultCode, data)
    }

    private fun updateUiWithUser(model: LoggedInUserView) {
        val welcome = getString(R.string.welcome) + model.displayName
        // TODO : initiate successful logged in experience
        val appContext = context?.applicationContext ?: return
        Toast.makeText(appContext, welcome, Toast.LENGTH_LONG).show()
    }

    private fun showLoginFailed(@StringRes errorString: Int) {
        val appContext = context?.applicationContext ?: return
        Toast.makeText(appContext, errorString, Toast.LENGTH_LONG).show()
    }
}

