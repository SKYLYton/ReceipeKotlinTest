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

    fun getHistory(): Single<List<HistoryItem>> {
        return dao.getAllSearchItem().map { it ->
            val list: MutableList<HistoryItem> = mutableListOf()

            it.forEach {
                list.add(historyModelMapper.mapToHistoryItem(it))
            }

            list
        }
    }

}