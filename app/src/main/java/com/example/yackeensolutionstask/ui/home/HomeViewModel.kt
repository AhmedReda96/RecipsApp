package com.example.yackeensolutionstask.ui.home

import alphagroup.veramovieapp.com.UI.TvPart.API.Client
import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.yackeensolutionstask.db.MainDatabase
import com.example.yackeensolutionstask.db.RecipeDao
import com.example.yackeensolutionstask.db.RecipeEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class HomeViewModel(application: Application) : AndroidViewModel(application) {

    private var recipeDao: RecipeDao? = null
    var recipesLiveData: LiveData<List<RecipeEntity>>? = null

    init {

        recipeDao = MainDatabase.Companion.getMainDatabase(application).recipeDao()
    }

    fun fillDataToRoom() {

        GlobalScope.launch(Dispatchers.Main) {
            val response = Client.getClient.getRecipesData()
            if (response?.isSuccessful!!) {
                Log.d("Tag", "Recipes : get Data from api successfully")

                GlobalScope.launch(Dispatchers.Main) {
                    recipeDao?.insertRecipes(response.body() as List<RecipeEntity>)
                    if (response?.isSuccessful!!) {
                        Log.d("Tag", "Recipes : add Data to room successfully")

                    } else {
                        Log.d("Tag", "Recipes error: failed add Data to room ")

                    }
                }

            } else {
                Log.d("Tag", "Recipes : failed to get Data from api")

            }
        }
    }

    fun getRecipesFromRoom() {
        recipesLiveData = recipeDao?.getRecipeDataFromRoom()
    }

    fun getHighestRateCalories() {
        recipesLiveData = recipeDao?.getHighestRateCalories()
    }
    fun getHighestRateFats() {
        recipesLiveData = recipeDao?.getHighestRatefats()
    }



}


