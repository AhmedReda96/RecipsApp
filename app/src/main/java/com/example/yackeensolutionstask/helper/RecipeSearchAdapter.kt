package com.example.yackeensolutionstask.helper

import android.content.Context
import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Parcel
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.yackeensolutionstask.R
import com.example.yackeensolutionstask.db.RecipeEntity
import com.example.yackeensolutionstask.ui.details.RecipeDetailsScreen
import kotlinx.android.synthetic.main.recpie_item_model.view.*

class RecipeSearchAdapter() : RecyclerView.Adapter<RecipeSearchAdapter.ViewHolder>() {
    private var recipesList: List<RecipeEntity> = ArrayList<RecipeEntity>()
    private lateinit var context: Context

    constructor(parcel: Parcel) : this() {

    }

    constructor(recipes: List<RecipeEntity>, contextt: Context) : this() {
        recipesList = recipes
        context = contextt


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.recpie_search_item_model, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return recipesList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var model: RecipeEntity = recipesList.get(position)



        Glide.with(context).load(model.image).listener(object : RequestListener<Drawable> {


            override fun onResourceReady(p0: Drawable?, p1: Any?, target: com.bumptech.glide.request.target.Target<Drawable>?, dataSource: com.bumptech.glide.load.DataSource?, p4: Boolean): Boolean {
                holder.progress.visibility = View.GONE
                return false
            }

            override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean {
                holder.progress.visibility = View.GONE
                holder.recipeImg.setImageResource(R.color.colorPrimary)
                return false

            }
        }).into(holder.recipeImg)

        Glide.with(context).load(model.image).listener(object : RequestListener<Drawable> {
            override fun onLoadFailed(
                p0: GlideException?,
                p1: Any?,
                p2: com.bumptech.glide.request.target.Target<Drawable>?,
                p3: Boolean
            ): Boolean {
                holder.progress.visibility = View.GONE
                holder.recipeImg.setImageResource(R.color.colorPrimary)
                return false
            }

            override fun onResourceReady(
                p0: Drawable?,
                p1: Any?,
                p2: com.bumptech.glide.request.target.Target<Drawable>?,
                p3: com.bumptech.glide.load.DataSource?,
                p4: Boolean
            ): Boolean {
                holder.progress.visibility = View.GONE
                return false
            }
        }).into(holder.recipeImg)


        if (model.name != null) {
            holder.recipeName.text = model.name
            holder.recipeName.visibility = View.VISIBLE

        } else {

            holder.recipeName.visibility = View.GONE


        }

        if (model.description != null) {
            holder.recipeDetails.text = model.description
            holder.recipeDetails.visibility = View.VISIBLE

        } else {

            holder.recipeDetails.visibility = View.GONE


        }



        holder.view.setOnClickListener {

           val intent = Intent(context, RecipeDetailsScreen::class.java)
            intent.putExtra("id",model.id )
            intent.putExtra("calories",model.calories )
            intent.putExtra("carbos",model.carbos )
            intent.putExtra("fats",model.fats )
            intent.putExtra("headline",model.headline )
            intent.putExtra("proteins",model.proteins )
            intent.putExtra("time",model.time )
            intent.putExtra("image",model.image )
            intent.putExtra("name",model.name )
            intent.putExtra("description",model.description )
            context.startActivity(intent)
        }
        holder.recipeImg.setOnClickListener {

            val intent = Intent(context, RecipeDetailsScreen::class.java)
            intent.putExtra("id",model.id )
            intent.putExtra("calories",model.calories )
            intent.putExtra("carbos",model.carbos )
            intent.putExtra("fats",model.fats )
            intent.putExtra("headline",model.headline )
            intent.putExtra("proteins",model.proteins )
            intent.putExtra("time",model.time )
            intent.putExtra("image",model.image )
            intent.putExtra("name",model.name )
            intent.putExtra("description",model.description )
            context.startActivity(intent)
        }

    }

    fun  deleteData(){
        recipesList.isNullOrEmpty()
    }

    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

        val recipeImg = view.recipeImg
        val recipeName = view.recipeName
        val recipeDetails = view.recipeDetails

        val progress = view.progress


    }


}
