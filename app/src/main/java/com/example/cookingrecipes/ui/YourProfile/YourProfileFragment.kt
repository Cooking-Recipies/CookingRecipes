package com.example.cookingrecipes.ui.YourProfile

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.cookingrecipes.MainActivity
import com.example.cookingrecipes.R
import com.example.cookingrecipes.data.model.ProfileModel
import com.example.cookingrecipes.data.repositories.SelectedProfileRepository
import com.example.cookingrecipes.ui.AddRecipe.AddNewRecipeFragment
import com.google.android.material.floatingactionbutton.FloatingActionButton

class YourProfileFragment : Fragment() {

    private lateinit var yourProfileViewModel: YourProfileViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        yourProfileViewModel =ViewModelProvider(this)[YourProfileViewModel::class.java]
        val root = inflater.inflate(R.layout.fragment_yourprofile, container, false)
        val yourName: TextView = root.findViewById(R.id.your_name)
        val description: TextView = root.findViewById(R.id.your_description)
        val yourPhoto: ImageView = root.findViewById(R.id.your_photo)
        yourProfileViewModel.fetchSelectedProfile()
        yourProfileViewModel.profileModelLiveData?.observe(viewLifecycleOwner, Observer {
            if(it!=null){
                yourName.text = it.name
                description.text = it.description}
            else{
                yourName.text = "no data"
            }
        })
//        val textView: TextView = root.findViewById(R.id.text_yourprofile)
//        yourProfileViewModel.text.observe(viewLifecycleOwner, Observer {
//            textView.text = it
//        })

        return root
    }
}