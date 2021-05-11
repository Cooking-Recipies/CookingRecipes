package com.example.cookingrecipes.ui.AllRecipes

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView
import com.example.cookingrecipes.R
import com.example.cookingrecipes.data.model.DataRecipes
import com.example.cookingrecipes.data.model.RecipesModel

import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_recipe.view.*
import kotlinx.android.synthetic.main.fragment_yourprofile.*
import kotlinx.android.synthetic.main.recipe_cardview.view.*

class RecipesAdapter(private val listOfRecipes: DataRecipes, val onClickListener: OnClickListener) : RecyclerView.Adapter<RecipesAdapter.RecipesViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipesViewHolder {
        return RecipesViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.recipe_cardview,parent,false))
    }

    inner class RecipesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val title: TextView
        val category : TextView
        val tags : TextView
        val photo : ImageView
        val likesCounter: TextView
        val like: ImageView
//
        init{
            title = itemView.findViewById(R.id.TitleTV)
            category = itemView.findViewById(R.id.Categories)
            tags = itemView.findViewById(R.id.tags)
            photo = itemView.findViewById(R.id.Recipe_photo)
            likesCounter = itemView.findViewById(R.id.LikesNumber)
            like = itemView.findViewById(R.id.imageButton)
        }

    }

    override fun onBindViewHolder(holder: RecipesViewHolder, position: Int) {
        val item = listOfRecipes.data[position]
        holder.itemView.apply {
            holder.itemView.setOnClickListener {
                onClickListener.onItemClick(item,item.recipe_id)
            }
            holder.likesCounter.text = item.likes.likes_count.toString()
            holder.title.text = item.title
            holder.category.text = item.category
            holder.tags.text = item.tags.toString()
//            Picasso.get()
//                .load(item.photos.first()?.data?.url)
//                .into(holder.photo)
        }


    }

    override fun getItemCount(): Int {
        return listOfRecipes.data.size
    }

    interface OnClickListener{
        fun onItemClick(recipesModel: RecipesModel,id:Int)
    }
}