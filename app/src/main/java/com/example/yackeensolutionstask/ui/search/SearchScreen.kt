package com.example.yackeensolutionstask.ui.search

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import com.example.yackeensolutionstask.R
import com.example.yackeensolutionstask.Utils.Session.StoreData
import com.example.yackeensolutionstask.helper.RecipeSearchAdapter
import kotlinx.android.synthetic.main.activity_search_screen.*

class SearchScreen : AppCompatActivity() {
    private lateinit var viewModel: SearchScreenViewModel

    private lateinit var adapter: RecipeSearchAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_screen)

        init()

    }

    private fun init() {
        viewModel = ViewModelProviders.of(this).get(SearchScreenViewModel::class.java)

        recipeSearchRV.apply {
            layoutManager =
                GridLayoutManager(this@SearchScreen, 1, GridLayoutManager.VERTICAL, false)
            itemAnimator = DefaultItemAnimator()
        }



        backBtn.setOnClickListener {
            finish()
        }


        searchBar.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextChange(newText: String): Boolean {

                if (newText.isEmpty()) {
                    swipe.visibility = View.GONE
                } else {
                    swipe.isRefreshing = true
                    viewModel.getSearchDate(newText, this@SearchScreen)
                }


                return false
            }

            override fun onQueryTextSubmit(query: String): Boolean {
                viewModel.getSearchDate(query, this@SearchScreen)



                return false
            }

        })

        viewModel.searchLiveData?.observe(this, Observer { recipesList ->

            Log.d("Tag", "Recipes : size of research recipes list = " + recipesList.size)
            if (recipesList.size > 0) {
                swipe.visibility = View.VISIBLE
                adapter = RecipeSearchAdapter(recipesList, this@SearchScreen)
                adapter.notifyDataSetChanged()
                recipeSearchRV.adapter = adapter
                swipe.isRefreshing = false

            } else {
                swipe.isRefreshing = false
                swipe.visibility = View.GONE

            }


        })


    }

}
