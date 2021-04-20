package com.example.cookingrecipes.ui.YourProfile

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.cookingrecipes.R
import com.example.cookingrecipes.ui.AddRecipe.AddNewRecipeFragment
import com.google.android.material.floatingactionbutton.FloatingActionButton

class YourProfileFragment : Fragment() {

    private lateinit var yourProfileViewModel: YourProfileViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        yourProfileViewModel =
                ViewModelProviders.of(this).get(YourProfileViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_yourprofile, container, false)
//        val textView: TextView = root.findViewById(R.id.text_yourprofile)
//        yourProfileViewModel.text.observe(viewLifecycleOwner, Observer {
//            textView.text = it
//        })

        return root
    }
}