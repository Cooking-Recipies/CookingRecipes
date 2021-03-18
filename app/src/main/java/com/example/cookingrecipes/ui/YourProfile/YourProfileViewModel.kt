package com.example.cookingrecipes.ui.YourProfile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class YourProfileViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is YourProfile Fragment"
    }
    val text: LiveData<String> = _text
}