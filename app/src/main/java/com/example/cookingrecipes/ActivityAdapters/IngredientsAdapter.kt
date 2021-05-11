package com.example.cookingrecipes.ActivityAdapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView
import com.example.cookingrecipes.R
import com.example.cookingrecipes.data.model.ComponentsModel
import kotlinx.android.synthetic.main.ingredients_cardview.view.*

class IngredientsAdapter(private val list: List<ComponentsModel>) : RecyclerView.Adapter<IngredientsAdapter.ViewHolder>(){
    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val ingredient: TextView
        val amount: TextView
        init {
            ingredient = itemView.findViewById(R.id.NameOfIngredient)
            amount = itemView.findViewById(R.id.Amount)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.ingredients_cardview,parent,false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        holder.itemView.apply {
            holder.ingredient.text = item.name
            holder.amount.text = item.quantity
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

}