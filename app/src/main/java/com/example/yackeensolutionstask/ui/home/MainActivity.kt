package com.example.yackeensolutionstask.ui.home

import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import com.example.yackeensolutionstask.R
import com.example.yackeensolutionstask.Utils.InternetConnection.ConnectionDetector
import com.example.yackeensolutionstask.Utils.Session.StoreData
import com.example.yackeensolutionstask.helper.RecipeAdapter
import com.example.yackeensolutionstask.ui.search.SearchScreen
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: HomeViewModel
    private lateinit var adapter: RecipeAdapter
    private lateinit var progressDialog: ProgressDialog
    private lateinit var cd: ConnectionDetector
    private lateinit var storeData: StoreData

    private var isInternetPresent = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()

    }


    private fun init() {
        viewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)

        recipeRV.apply {
            layoutManager =
                GridLayoutManager(this@MainActivity, 1, GridLayoutManager.VERTICAL, false)
            itemAnimator = DefaultItemAnimator()
        }
        storeData = StoreData(this)

        progressDialog = ProgressDialog(this)
        progressDialog.setMessage(this.resources.getString(R.string.loading))
        progressDialog.setCancelable(false)

        cd = ConnectionDetector(this)

        getDataFromRoom()

        searchBtn.setOnClickListener {
            startActivity(Intent(this@MainActivity, SearchScreen::class.java))
        }
        retryBtn.setOnClickListener {

            getDataFromRoom()

        }


        sortBtn.setOnClickListener {
            val mySortAlertDialog: AlertDialog.Builder = AlertDialog.Builder(this)
            mySortAlertDialog.setTitle("Sort by?")
            val r = arrayOf("All", "The highest rate of fats", "The highest rate of calories")
            mySortAlertDialog.setSingleChoiceItems(r, 0, null)

            mySortAlertDialog.setPositiveButton("OK",
                DialogInterface.OnClickListener { dialog, which ->
                    if ((dialog as AlertDialog).getListView().getCheckedItemPosition() == 0) {
                        sortBtn.text = "All"
                        storeData.setIndex(0)
                        viewModel.getRecipesFromRoom()
                        getDataFromRoom()
                    }
                    if ((dialog as AlertDialog).getListView().getCheckedItemPosition() == 1) {

                        storeData.setIndex(1)
                        sortBtn.text = "The highest rate of fats"
                        viewModel.getHighestRateFats()
                        getDataFromRoom()
                    }
                    if ((dialog as AlertDialog).getListView().getCheckedItemPosition() == 2) {
                        storeData.setIndex(2)
                        sortBtn.text = "The highest rate of calories"
                        viewModel.getHighestRateCalories()
                        getDataFromRoom()
                    }

                })
            mySortAlertDialog.create().show()

        }



        swipe.setOnRefreshListener {
            getDataFromRoom()
        }
    }

    private fun getDataFromRoom() {
        progressDialog.show()
        viewModel.recipesLiveData?.observe(this, Observer { recipesList ->
            if (recipesList.isNotEmpty()) {
                Log.d("Tag", "Recipes : size of recipes list = " + recipesList.size)

                adapter = RecipeAdapter(recipesList, this@MainActivity)
                adapter.notifyDataSetChanged()
                recipeRV.adapter = adapter
                progressDialog.dismiss()
                swipe.isRefreshing = false
            } else {
                getRecipesDataOnline()
            }


        })

    }

    private fun getRecipesDataOnline() {

        isInternetPresent = cd.isConnectingToInternet
        if (isInternetPresent) {
            connectionLin.visibility = View.GONE
            swipe.visibility = View.VISIBLE

            viewModel.fillDataToRoom()

        } else {

            connectionLin.visibility = View.VISIBLE
            swipe.visibility = View.GONE
            progressDialog.dismiss()

        }
    }


    override fun onStart() {
        super.onStart()


        if (storeData.getIndex() == 0) {
            sortBtn.text = "All"
            viewModel.getRecipesFromRoom()
            getDataFromRoom()

        }
        if (storeData.getIndex() == 1) {
            sortBtn.text = "The highest rate of fats"
            viewModel.getHighestRateFats()
            getDataFromRoom()
        }
        if (storeData.getIndex() == 2) {
            sortBtn.text = "The highest rate of calories"
            viewModel.getHighestRateCalories()
            getDataFromRoom()
        }


    }


}
