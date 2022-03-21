package com.example.listresep.adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.listresep.R
import com.example.listresep.model.RecipeModel

class RecipeAdapter(private val activity : Activity) : RecyclerView.Adapter<RecipeAdapter.RecipeVH>() {

    private var recipeList : MutableList<RecipeModel> = ArrayList()

    class RecipeVH(view : View) : RecyclerView.ViewHolder(view){
        private var imageThumbnail: ImageView = view.findViewById(R.id.imageThumbnail)
        private var tvTitleRecipe : TextView = view.findViewById(R.id.tvTitleRecipe)
        private var tvDifficulty : TextView = view.findViewById(R.id.tvDificulty)
        private var tvPortion : TextView = view.findViewById(R.id.tvPortion)
        private var tvTimes : TextView = view.findViewById(R.id.tvTimes)

        fun bind(data: RecipeModel, activity: Activity){
            tvTitleRecipe.text = data.title
            tvDifficulty.text = data.dificulty
            tvPortion.text = data.portion
            tvTimes.text = data.times
            Glide.with(activity)
                .load(data.thumb)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageThumbnail)
        }

    }

    fun setRecipeList(recipeList : MutableList<RecipeModel>){
        this.recipeList = recipeList
    }

    fun addRecipeList(recipeList: MutableList<RecipeModel>){
        recipeList.addAll(recipeList)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeAdapter.RecipeVH {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recipe_list, parent,false)
        return RecipeVH(view)
    }

    override fun onBindViewHolder(holder: RecipeAdapter.RecipeVH, position: Int) {
        holder.bind(recipeList[position], activity)
    }

    override fun getItemCount(): Int {
        return recipeList.size
    }
}