package com.example.cookingrecipes.ui.YourProfile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cookingrecipes.data.model.ProfileModel
import com.example.cookingrecipes.data.repositories.MyProfileRepository
import com.example.cookingrecipes.data.repositories.SelectedProfileRepository

class YourProfileViewModel : ViewModel() {

    private var selectedProfileRepository: SelectedProfileRepository?=null
    var profileModelLiveData : LiveData<ProfileModel>?=null

    init {
        selectedProfileRepository = SelectedProfileRepository()
        profileModelLiveData = MutableLiveData()
    }
    fun fetchSelectedProfile(){
        profileModelLiveData = selectedProfileRepository?.fetchSelectedProfile()
    }
}