package com.example.cookingrecipes.data.model

data class RecipeDetailModel(
    var user_profile_id: Int,
    var recipe_id: Int,
    var title: String,
    var category: String,
    var number_of_people: Int,
    var preparing_time: String,
    var instruction: String,
    var created_by_logged_user: Boolean,
    var photos: List<PhotoModel>?,
    var components: List<ComponentsModel>,
    var tags: List<String>,
    var logged_user_liked: Boolean,
    var likes_count: Int

)
