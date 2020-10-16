package com.example.yackeensolutionstask.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import io.reactivex.Observable

@Dao
interface RecipeDao {

    @Query("SELECT * FROM recipe")
    fun getRecipeDataFromRoom(): LiveData<List<RecipeEntity>>

    @Insert
    suspend fun insertRecipes(recipesList: List<RecipeEntity>)


    @Query("SELECT * FROM recipe WHERE name LIKE '%' || :value  || '%'")
    fun searchRecipe(value: String?): Observable<List<RecipeEntity>>



    @Query("SELECT * FROM recipe ORDER BY calories DESC " )
    fun getHighestRateCalories(): LiveData<List<RecipeEntity>>

    @Query("SELECT * FROM recipe ORDER BY fats DESC " )
    fun getHighestRatefats(): LiveData<List<RecipeEntity>>


}