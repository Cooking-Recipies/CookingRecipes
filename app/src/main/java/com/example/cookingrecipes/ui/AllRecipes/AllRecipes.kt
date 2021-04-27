package com.example.cookingrecipes.ui.AllRecipes

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.cookingrecipes.AddNewRecipeActivity
import com.example.cookingrecipes.MainActivity
import com.example.cookingrecipes.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

class AllRecipes : Fragment() {

    private lateinit var addBtn: FloatingActionButton
    companion object {
        fun newInstance() = AllRecipes()
    }

    private lateinit var viewModel: AllRecipesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.all_recipes_fragment, container, false)
        addBtn = root.findViewById(R.id.addBtn)
        addBtn.setOnClickListener {
            val intent = Intent(activity,AddNewRecipeActivity::class.java)
            activity?.startActivity(intent)
        }
        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(AllRecipesViewModel::class.java)
    }

}