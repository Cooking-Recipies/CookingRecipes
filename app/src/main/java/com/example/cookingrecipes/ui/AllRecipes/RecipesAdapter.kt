package com.example.cookingrecipes.ui.AllRecipes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView
import com.example.cookingrecipes.R
import com.example.cookingrecipes.data.model.DataRecipes

import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_recipe.view.*
import kotlinx.android.synthetic.main.fragment_yourprofile.*
import kotlinx.android.synthetic.main.recipe_cardview.view.*

class RecipesAdapter : RecyclerView.Adapter<RecipesAdapter.RecipesViewHolder>() {
    private var data : List<DataRecipes>?=null

    fun setData(list: List<DataRecipes>){
        data = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipesViewHolder {
        return RecipesViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.recipe_cardview,parent,false))
    }

    class RecipesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val title = itemView.TitleTV
        val category = itemView.Categories
        val tags = itemView.tags
        val photo = itemView.Recipe_photo
//        fun bindView(item: DataRecipes?){
//            itemView.TitleTV.text = item?.data?.title
//            itemView.Categories.text = item?.data?.category
//            itemView.tags.text = item?.data?.tags.toString()
//            Picasso.get()
//                .load(item?.data?.photo?.data?.url)
//                .into(itemView.Recipe_photo)
//        }
    }

    override fun onBindViewHolder(holder: RecipesViewHolder, position: Int) {
        val item = data?.get(position)
        holder.title.text = item?.data?.title
        holder.category.text = item?.data?.category
        holder.tags.text = item?.data?.tags.toString()
        Picasso.get()
            .load(item?.data?.photos?.data?.url)
            .into(holder.photo)
    }

    override fun getItemCount(): Int {
        return data?.size ?:0
    }

}