package com.example.cookingrecipes.ui.AllRecipes

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cookingrecipes.*
import com.example.cookingrecipes.Api.RetrofitClient
import com.example.cookingrecipes.data.model.DataProfile
import com.example.cookingrecipes.data.model.DataRecipes
import com.example.cookingrecipes.data.model.RecipesModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.all_recipes_fragment.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class AllRecipes : Fragment(), RecipesAdapter.OnClickListener {
    private lateinit var addBtn: FloatingActionButton
    private lateinit var recipesList: DataRecipes
    private lateinit var recipesAdapter: RecipesAdapter

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
        getRecipesList()
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }

    private fun getRecipesList(){
        RetrofitClient.instance.fetchAllRecipes().enqueue(object: Callback<DataRecipes> {
            override fun onResponse(
                call: Call<DataRecipes>,
                response: Response<DataRecipes>
            ) {
                response.body()?.let {
                    recipesList = it
                }
                initRecyclerView(view)
            }

            override fun onFailure(call: Call<DataRecipes>, t: Throwable) {
                println(t.message)
            }
        })
    }

    private fun initRecyclerView(view: View?) {
        tasksRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        recipesAdapter = RecipesAdapter(recipesList,this)
        tasksRecyclerView.adapter = recipesAdapter
    }

    override fun onItemClick(recipesModel: RecipesModel,id:Int) {
            val intent = Intent(activity, SelectedRecipe::class.java)
            intent.putExtra("ID",id)
            activity?.startActivity(intent)
    }


}