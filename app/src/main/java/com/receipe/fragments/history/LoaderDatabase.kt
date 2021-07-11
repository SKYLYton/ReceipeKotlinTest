package com.receipe.fragments.history

import com.receipe.fragments.history.model.HistoryItem
import com.receipe.room.model.RecipeDao
import io.reactivex.Flowable
import io.reactivex.Single
import javax.inject.Inject

class LoaderDatabase @Inject constructor(
    var historyModelMapper: HistoryModelMapper,
    var dao: RecipeDao
) {

    suspend fun getHistory(): List<HistoryItem> {
        val result = dao.getAllSearchItem()
        val list: MutableList<HistoryItem> = mutableListOf()

        result.forEach {
            list.add(historyModelMapper.mapToHistoryItem(it))
        }

        return list
    }

}