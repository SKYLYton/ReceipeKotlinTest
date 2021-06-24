package com.receipe.fragments.history

import com.receipe.fragments.history.model.HistoryItem
import com.receipe.fragments.search_recipe.model.ResultSearchRecipe
import com.receipe.room.model.recipe.RecipeItem
import com.receipe.room.model.recipe.SearchItem

class HistoryModelMapper {
    fun mapToHistoryItem(searchItem: SearchItem): HistoryItem {
        val historyItem = HistoryItem()
        historyItem.name = searchItem.name
        return historyItem
    }

}