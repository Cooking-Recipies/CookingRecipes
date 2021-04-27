package com.example.cookingrecipes.Api

import com.example.cookingrecipes.data.model.LoginModel
import com.example.cookingrecipes.data.model.PhotoModel
import com.example.cookingrecipes.data.model.ProfileModel
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST


interface ApiInterface {
    @GET("profiles")
    fun fetchAllProfiles(): Call<List<ProfileModel>>

    @GET("users/me/photos")
    fun fetchAllPhotos(): Call<List<PhotoModel>>

    @GET("profiles/me")
    fun fetchMyProfile(): Call<ProfileModel>

    @GET("profiles/1")
    fun fetchSelectedProfile(): Call<ProfileModel>

    @Headers("token")
    @POST("login")
    fun loginUser(@Body info: LoginModel): Call<ResponseBody>
}