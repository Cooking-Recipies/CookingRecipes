package com.example.cookingrecipes.data.repositories

//import com.example.cookingrecipes.Api.ApiClient
import com.example.cookingrecipes.Api.ApiInterface
import com.example.cookingrecipes.data.LoginDataSource
import com.example.cookingrecipes.data.Result
import com.example.cookingrecipes.data.model.LoggedInUser

/**
 * Class that requests authentication and user information from the remote data source and
 * maintains an in-memory cache of login status and user credentials information.
 */

class LoginRepository(val dataSource: LoginDataSource) {

    // in-memory cache of the loggedInUser object
    var user: LoggedInUser? = null
        private set

    val isLoggedIn: Boolean
        get() = user != null
    private var apiInterface: ApiInterface?=null
    init {
//        apiInterface = ApiClient.getApiClient().create(ApiInterface::class.java)
        user = null
    }

    fun logout() {
        user = null
        dataSource.logout()
    }


    fun login(username: String, password: String): Result<LoggedInUser> {
        // handle login
        val result = dataSource.login(username, password)

        if (result is Result.Success) {
            setLoggedInUser(result.data)
        }

        return result
    }

    private fun setLoggedInUser(loggedInUser: LoggedInUser) {
        this.user = loggedInUser
        // If user credentials will be cached in local storage, it is recommended it be encrypted
        // @see https://developer.android.com/training/articles/keystore
    }
}