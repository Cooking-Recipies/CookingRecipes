package com.example.cookingrecipes.data.repositories

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.cookingrecipes.Api.ApiInterface
import com.example.cookingrecipes.Api.RetrofitClient
import com.example.cookingrecipes.data.model.ProfileModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SelectedProfileRepository {
    private var apiInterface: ApiInterface?=null

//    init {
//        apiInterface = RetrofitClient.getApiClient().create(ApiInterface::class.java)
//    }

//    fun fetchSelectedProfile(): ProfileModel {
//        val data = ProfileModel(photo = null)
//
//        apiInterface?.fetchSelectedProfile()?.enqueue(object : Callback<ProfileModel> {
//
//            override fun onFailure(call: ProfileModel, t: Throwable) {
//                data.value = null
//            }
//
//            override fun onResponse(
//                call: Call<ProfileModel>,
//                response: Response<ProfileModel>
//            ) {
//
//                val res = response.body()
//                if (response.code() == 200 && res != null) {
//                    data.value = res
//                } else {
//                    data.value = null
//                }
//            }
//
//        })
//        return data
//    }
}