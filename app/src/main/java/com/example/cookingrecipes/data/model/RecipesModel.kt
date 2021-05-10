package com.example.cookingrecipes.data.model

import com.google.gson.annotations.SerializedName

data class RecipesModel(
    @SerializedName("data")
    var user_profile_id : Int,
    var recipe_id : Int,
    var title :String,
    var category: String,
    var tags: List<String>,
    var photos: List<DataPhoto?>,
    var likes: LikesModel?
)
