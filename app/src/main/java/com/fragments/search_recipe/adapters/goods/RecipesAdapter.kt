package com.aymarja.adapters.goods

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.receipe.R
import com.recipes.retrofit.model.recipe.Hit
import com.recipes.retrofit.model.recipe.RecipeModel

class RecipesAdapter(private var list: List<Hit>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var onClickItemListener: OnClickItemListener? = null

    interface OnClickItemListener {
        fun onClick(orderModel: Hit?, pos: Int)
    }

    fun removeListener(){
        onClickItemListener = null
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.recipe_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        initItem(holder as ViewHolder, position)
    }

    private fun initItem(viewHolder: ViewHolder, position: Int) {
        val recipeModel: Hit = list[position]

        viewHolder.textViewName.text = recipeModel.recipe?.label
        viewHolder.textViewCalories.text = recipeModel.recipe?.calories.toString()

        viewHolder.mainView.setOnClickListener {
            onClickItemListener?.onClick(recipeModel, position)
        }

    }

    override fun getItemCount(): Int {
        return list.size
    }

    // Static inner class to initialize the views of rows
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val mainView: View
        val textViewName: TextView
        val textViewCalories: TextView
        val imageView: ImageView

        init {
            mainView = view
            textViewName = view.findViewById(R.id.textViewName)
            textViewCalories = view.findViewById(R.id.textViewCalories)
            imageView = view.findViewById(R.id.imageView)
        }
    }
}