package com.receipe.fragments.search_recipe.adapters.goods

import com.recipes.retrofit.model.recipe.Hit

interface OnClickItemListener {
    fun onClick(orderModel: Hit?, pos: Int)
}