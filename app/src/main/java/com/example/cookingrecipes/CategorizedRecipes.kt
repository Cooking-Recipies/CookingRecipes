package com.example.cookingrecipes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cookingrecipes.Api.RetrofitClient
import com.example.cookingrecipes.data.model.DataRecipes
import com.example.cookingrecipes.data.model.RecipesModel
import com.example.cookingrecipes.ui.AllRecipes.RecipesAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.all_recipes_fragment.*
import kotlinx.android.synthetic.main.fragment_categories.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class CategorizedRecipes : AppCompatActivity(), RecipesAdapter.OnClickListener {
    private lateinit var addBtn: FloatingActionButton
    private lateinit var recipesListCategorized: DataRecipes
    private lateinit var recipesAdapter: RecipesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_categorized_recipes)
        val actionbar = supportActionBar

        actionbar!!.title = "Categorized Recipe"
        addBtn = findViewById(R.id.addBtn)
        addBtn.setOnClickListener {
            val intent = Intent(this,AddNewRecipeActivity::class.java)
            startActivity(intent)
        }

        getRecipesList()
    }

    private fun getRecipesList() {
        var categoryName = intent.getStringExtra("CategoryName")

        RetrofitClient.instance.fetchAllRecipes().enqueue(object: Callback<DataRecipes> {
            override fun onResponse(
                call: Call<DataRecipes>,
                response: Response<DataRecipes>
            ) {
                response.body()?.let {
                    recipesListCategorized = it
                    recipesListCategorized.data = it.data.filter {it.category == categoryName} as MutableList<RecipesModel>

                }

                initRecyclerView(content)
            }

            override fun onFailure(call: Call<DataRecipes>, t: Throwable) {
                println(t.message)
            }
        })
    }

    private fun initRecyclerView(view: View?) {
        tasksRecyclerView.layoutManager = LinearLayoutManager(this)
        recipesAdapter = RecipesAdapter(recipesListCategorized,this)
        tasksRecyclerView.adapter = recipesAdapter
    }

    override fun onItemClick(recipesModel: RecipesModel, id: Int) {
        val intent = Intent(this, SelectedRecipe::class.java)
        intent.putExtra("ID",id)
        startActivity(intent)
    }
}