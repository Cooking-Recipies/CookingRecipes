package com.example.cookingrecipes.data.model

import com.google.gson.annotations.SerializedName

data class ProfileModel (

    var profile_id: Int,
    var name: String,
    var description: String,
    var photo_id: DataPhoto
        )