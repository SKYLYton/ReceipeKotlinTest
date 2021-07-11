package com.receipe.room.model.recipe

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity()
data class RecipeItem(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0,
    @ColumnInfo(name = "name")
    var name: String? = "",
    @ColumnInfo(name = "calories")
    var calories: Double? = 0.0
)