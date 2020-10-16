package com.example.yackeensolutionstask.ui.details

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.example.yackeensolutionstask.R
import kotlinx.android.synthetic.main.activity_recipe_details_screen.*
import kotlin.math.log

class RecipeDetailsScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe_details_screen)

        init()
    }

    private fun init() {


        collapsingtoolbar.setTitleEnabled(true)
        collapsingtoolbar.setExpandedTitleTextAppearance(R.style.AppBarExpanded)
        collapsingtoolbar.setCollapsedTitleTextAppearance(R.style.AppBarCollapsed)
        setSupportActionBar(toolbar)
        if (supportActionBar != null) {
            backBtn.setVisibility(View.VISIBLE)
        } else {
            backBtn.setVisibility(View.GONE)
        }


        calories.text = intent.getStringExtra("calories")
        fats.text = intent.getStringExtra("fats")
        headline.text = intent.getStringExtra("headline")
        carbos.text = intent.getStringExtra("carbos")
        proteins.text = intent.getStringExtra("proteins")
        time.text = intent.getStringExtra("time")
        collapsingtoolbar.title = intent.getStringExtra("name")
        description.text = intent.getStringExtra("description")


        Glide.with(this).load(intent.getStringExtra("image"))
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(p0: GlideException?, p1: Any?, p2: com.bumptech.glide.request.target.Target<Drawable>?, p3: Boolean): Boolean {
                    progress.visibility = View.GONE
                    recipeImg.setImageResource(R.color.colorPrimary)
                    return false
                }

                override fun onResourceReady(p0: Drawable?, p1: Any?, p2: com.bumptech.glide.request.target.Target<Drawable>?, p3: com.bumptech.glide.load.DataSource?, p4: Boolean): Boolean {
                    progress.visibility = View.GONE
                    return false
                }
            }).into(recipeImg)







        backBtn.setOnClickListener {
            onBackPressed()
        }


    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()

    }
}
