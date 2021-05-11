package com.example.cookingrecipes.ui.Categories

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.cookingrecipes.R



class CategoriesRecyclerViewAdapter(
    private val categories: List<String>
) : RecyclerView.Adapter<CategoriesRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_categories, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = categories[position]
        holder.contentView.text = item
    }

    override fun getItemCount(): Int = categories.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val contentView: TextView = view.findViewById(R.id.content)

    }
}