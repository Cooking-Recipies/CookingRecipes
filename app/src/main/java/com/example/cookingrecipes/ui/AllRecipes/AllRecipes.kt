package com.example.cookingrecipes.ui.AllRecipes

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cookingrecipes.AddNewRecipeActivity
import com.example.cookingrecipes.Api.RetrofitClient
import com.example.cookingrecipes.MainActivity
import com.example.cookingrecipes.R
import com.example.cookingrecipes.data.model.DataProfile
import com.example.cookingrecipes.data.model.DataRecipes
import com.example.cookingrecipes.data.model.RecipesModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.all_recipes_fragment.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class AllRecipes : Fragment() {
    private lateinit var adapter: RecipesAdapter
    private lateinit var addBtn: FloatingActionButton

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

        RetrofitClient.instance.fetchAllRecipes().enqueue(object: Callback<List<DataRecipes>> {
            override fun onResponse(
                call: Call<List<DataRecipes>>,
                response: Response<List<DataRecipes>>
            ) {
                adapter = RecipesAdapter()
                tasksRecyclerView.layoutManager = LinearLayoutManager(requireContext())
                tasksRecyclerView.adapter = adapter
                response.body()?.let { adapter.setData(it) }
            }

            override fun onFailure(call: Call<List<DataRecipes>>, t: Throwable) {
                Toast.makeText(activity,"something went wrong with showing recipes",Toast.LENGTH_SHORT).show()
                println("wtf")
            }

        })


        return root

    }


}