package com.example.cookingrecipes.Api

import com.example.cookingrecipes.data.model.*
import com.google.gson.GsonBuilder
import com.google.gson.annotations.SerializedName
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*


interface ApiInterface {
    @GET("api/profiles")
    fun fetchAllProfiles(): List<ProfileModel>

    @GET("api/users/me/photos")
    fun fetchAllPhotos(): List<PhotoModel>

    @GET("api/profiles/me")
    fun fetchMyProfile(): Call<DataProfile>

    @GET("api/profiles/1")
    fun fetchSelectedProfile(): Call<DataProfile>

    @GET("api/recipes")
    fun fetchAllRecipes(): Call<DataRecipes>

    @GET("api/recipes/1")
    fun fetchSelectedRecipe(): Call<RecipeDetailModel>

    @FormUrlEncoded
    @POST("api/login")
    fun loginUser(@Field("email")email:String,@Field("password")password:String): Call<LoginRequest>

    @FormUrlEncoded
    @POST("api/register")
    fun userRegister(
        @Field("email") email:String,
        @Field("userName") username:String,
        @Field("password") password:String,
        @Field("password_confirmation")password_confirmation:String
    ):Call<userRegister>
}