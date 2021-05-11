package com.example.cookingrecipes.ui.Categories

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.cookingrecipes.Api.RetrofitClient
import com.example.cookingrecipes.CategorizedRecipes
import com.example.cookingrecipes.R
import com.example.cookingrecipes.SelectedRecipe
import com.example.cookingrecipes.data.model.DataRecipes
import com.example.cookingrecipes.ui.AllRecipes.RecipesAdapter
import kotlinx.android.synthetic.main.all_recipes_fragment.*
import kotlinx.android.synthetic.main.fragment_categories_list.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


/**
 * A fragment representing a list of Items.
 */
class CategoriesFragment : Fragment(), CategoriesRecyclerViewAdapter.OnClickListener {

    private lateinit var recipesList: DataRecipes
    private lateinit var categoriesAdapter: CategoriesRecyclerViewAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_categories_list, container, false)
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

        return view
    }
    private fun initRecyclerView(view: View?) {
        list.layoutManager = LinearLayoutManager(requireContext())
        val listOfCategories: MutableList<String> = mutableListOf()
        for (i in recipesList.data.indices){
            listOfCategories.add(recipesList.data.get(i).category)
        }
        categoriesAdapter = CategoriesRecyclerViewAdapter(listOfCategories.distinct(),this)
        list.adapter = categoriesAdapter
    }

    override fun onItemClick(category: String) {
        val intent = Intent(activity, CategorizedRecipes::class.java)
        intent.putExtra("CategoryName", category)
        activity?.startActivity(intent)
    }
}