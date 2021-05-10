package com.example.cookingrecipes.data.model

data class RecipesModel(
    var user_profile_id : Int,
    var recipe_id : Int,
    var title :String,
    var category: String,
    var tags: List<TagModel>,
    var photos: DataPhoto?,
    var likes: LikesModel?
)
