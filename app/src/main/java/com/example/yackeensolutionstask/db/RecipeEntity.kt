package com.example.yackeensolutionstask.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "recipe")
class RecipeEntity(
    @PrimaryKey @ColumnInfo(name = "id") var id: String,
    @ColumnInfo(name = "calories") var calories: String,
    @ColumnInfo(name = "carbos") var carbos: String,
    @ColumnInfo(name = "description") var description: String,
    @ColumnInfo(name = "difficulty") var difficulty: Int,
    @ColumnInfo(name = "fats") var fats: String,
    @ColumnInfo(name = "headline") var headline: String,
    @ColumnInfo(name = "image") var image: String,
    @ColumnInfo(name = "name") var name: String,
    @ColumnInfo(name = "proteins") var proteins: String,
    @ColumnInfo(name = "thumb") var thumb: String,
    @ColumnInfo(name = "time") var time: String,
    @ColumnInfo(name = "country") var country: String?
    )