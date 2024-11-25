package com.example.recipebook.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.domain.domain.model.RecipeModel

class DiffUtilCallBack(
    private val oldList: List<RecipeModel>,
    private val newList: List<RecipeModel>
): DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == oldList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == oldList[newItemPosition]
    }
}
