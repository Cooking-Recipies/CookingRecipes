package com.example.cookingrecipes.data.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.cookingrecipes.Api.ApiClient
import com.example.cookingrecipes.Api.ApiInterface
import com.example.cookingrecipes.data.model.ProfileModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyProfileRepository {
    private var apiInterface: ApiInterface?=null

    init {
        apiInterface = ApiClient.getApiClient().create(ApiInterface::class.java)
    }

    fun fetchMyProfile(): LiveData<ProfileModel> {
        val data = MutableLiveData<ProfileModel>()

        apiInterface?.fetchMyProfile()?.enqueue(object : Callback<ProfileModel> {

            override fun onFailure(call: Call<ProfileModel>, t: Throwable) {
                data.value = null
            }

            override fun onResponse(
                call: Call<ProfileModel>,
                response: Response<ProfileModel>
            ) {

                val res = response.body()
                if (response.code() == 200 && res != null) {
                    data.value = res
                } else {
                    data.value = null
                }
            }
        })
        return data
    }
}