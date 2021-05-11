package com.example.cookingrecipes

import android.app.Activity
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cookingrecipes.ActivityAdapters.IngredientsAdapter
import com.example.cookingrecipes.Api.RetrofitClient
import com.example.cookingrecipes.data.model.DataProfile
import com.example.cookingrecipes.data.model.DataRecipeDetailModel
import com.example.cookingrecipes.data.model.RecipeDetailModel
import com.example.cookingrecipes.ui.AllRecipes.AllRecipes
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_selected_recipe.*
import kotlinx.android.synthetic.main.fragment_yourprofile.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SelectedRecipe : AppCompatActivity() {
    private lateinit var ingredientsAdapter: IngredientsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_selected_recipe)
        val id = intent.getIntExtra("ID",1)
        RetrofitClient.instance.fetchSelectedRecipe(id).enqueue(object  : Callback<DataRecipeDetailModel> {
            override fun onResponse(call: Call<DataRecipeDetailModel>, response: Response<DataRecipeDetailModel>) {
                response.body()?.let { setData(it) }
            }

            override fun onFailure(call: Call<DataRecipeDetailModel>, t: Throwable) {
                println(t.message)
            }
        })
    }

    private fun setData(recipeDetailModel: DataRecipeDetailModel) {
        Recipe_title.text = recipeDetailModel.data.title
        CookingTime.text = recipeDetailModel.data.preparing_time
//        Picasso.get()
//            .load(recipeDetailModel.data.photos?.first()?.url)
//            .into(image)
        ingredientsList.layoutManager = LinearLayoutManager(this)
        ingredientsAdapter = IngredientsAdapter(recipeDetailModel.data.components)
        ingredientsList.adapter = ingredientsAdapter
        Description.text = recipeDetailModel.data.instruction

    }
}



