package com.example.recipebook.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.domain.domain.model.RecipeModel
import com.example.recipebook.R
import com.example.recipebook.databinding.RecipeItemBinding

class BrowsingRecipeAdapter(val listener: ListenerOnClickRecipe): RecyclerView.Adapter<BrowsingRecipeAdapter.RecipeHolder>() {

    private var recipeList = listOf<RecipeModel>()
    private lateinit var diffResult: DiffUtil.DiffResult

    class RecipeHolder(item: View): RecyclerView.ViewHolder(item) {
        val binding = RecipeItemBinding.bind(item)

        fun bind(recipe: RecipeModel, listener: ListenerOnClickRecipe) = with(binding) {
            recipeTitleTv.text = recipe.title
            Glide.with(root.context)
                .load(recipe.image)
                .into(recipeImageIv)
            itemView.setOnClickListener {
                listener.onClick(recipe)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recipe_item, parent, false)
        return RecipeHolder(view)
    }

    override fun getItemCount(): Int {
        return recipeList.size
    }

    override fun onBindViewHolder(holder: RecipeHolder, position: Int) {
        holder.bind(recipeList[position], listener)
    }

    fun setData(list: List<RecipeModel>) {
        diffResult = DiffUtil.calculateDiff(DiffUtilCallBack(recipeList, list))
        diffResult.dispatchUpdatesTo(this)
        recipeList = list
    }

    interface ListenerOnClickRecipe {
        fun onClick(recipe: RecipeModel)
    }
}
