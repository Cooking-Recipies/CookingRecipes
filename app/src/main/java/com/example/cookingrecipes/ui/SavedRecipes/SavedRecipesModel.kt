package com.example.cookingrecipes.ui.SavedRecipes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SavedRecipesModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is SavedRecipes Fragment"
    }
    val text: LiveData<String> = _text
}