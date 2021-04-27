package com.example.cookingrecipes.data.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.cookingrecipes.Api.ApiClient
import com.example.cookingrecipes.Api.ApiInterface
import com.example.cookingrecipes.data.model.PhotoModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PhotoRepository {
    private var apiInterface: ApiInterface?=null

    init {
        apiInterface = ApiClient.getApiClient().create(ApiInterface::class.java)
    }

    fun fetchAllPhotos(): LiveData<List<PhotoModel>> {
        val data = MutableLiveData<List<PhotoModel>>()

        apiInterface?.fetchAllPhotos()?.enqueue(object : Callback<List<PhotoModel>> {

            override fun onFailure(call: Call<List<PhotoModel>>, t: Throwable) {
                data.value = null
            }

            override fun onResponse(
                call: Call<List<PhotoModel>>,
                response: Response<List<PhotoModel>>
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