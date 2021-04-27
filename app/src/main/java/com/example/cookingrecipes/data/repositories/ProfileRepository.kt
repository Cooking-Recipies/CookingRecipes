package com.example.cookingrecipes.data.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.cookingrecipes.Api.ApiClient
import com.example.cookingrecipes.Api.ApiInterface
import com.example.cookingrecipes.data.model.ProfileModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProfileRepository {

    private var apiInterface:ApiInterface?=null

    init {
        apiInterface = ApiClient.getApiClient().create(ApiInterface::class.java)
    }

    fun fetchAllProfiles(): LiveData<List<ProfileModel>> {
        val data = MutableLiveData<List<ProfileModel>>()

        apiInterface?.fetchAllProfiles()?.enqueue(object : Callback<List<ProfileModel>> {

            override fun onFailure(call: Call<List<ProfileModel>>, t: Throwable) {
                data.value = null
            }

            override fun onResponse(
                call: Call<List<ProfileModel>>,
                response: Response<List<ProfileModel>>
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