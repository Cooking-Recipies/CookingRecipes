package com.example.cookingrecipes.data.model

data class ProfileModel (
    var profile_id: Int?=0,
    var name: String?="",
    var description: String?="",
    var photo: PhotoModel
        )