package com.example.recipebook.adapter.details_adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.recipebook.R
import com.example.recipebook.databinding.DetailsCheckItemBinding
import com.example.recipebook.databinding.DetailsHeaderItemBinding
import com.example.recipebook.databinding.DetailsTitleItemBinding

internal class DetailsAdapter: RecyclerView.Adapter<DetailsAdapter.BaseViewHolder>() {

    private var detailsItems = listOf<DetailsItem>()

    abstract class BaseViewHolder(item: View): RecyclerView.ViewHolder(item) {
        abstract fun bind(detailsItem: DetailsItem)
    }

    private class HeaderViewHolder(item: View) : BaseViewHolder(item) {
        val binding = DetailsHeaderItemBinding.bind(item)
        override fun bind(detailsItem: DetailsItem) {
            with(binding) {
                if (detailsItem is DetailsItem.HeaderDetailsItem) {
                    recipeTitleTv.text = detailsItem.title
                    servingsTv.text = detailsItem.servings.toString()
                    readyInMinutesTv.text = detailsItem.readyInMinutes.toString()
                    Glide.with(root.context)
                        .load(detailsItem.image)
                        .into(imageRecipeDetails)
                } else throw IllegalArgumentException("unknown item")
            }
        }
    }

    private class TitleDetailsViewHolder(item: View) : BaseViewHolder(item) {
        val binding = DetailsTitleItemBinding.bind(item)
        override fun bind(detailsItem: DetailsItem) {
            with(binding) {
                if (detailsItem is DetailsItem.TitleDetailsItem) {
                    ingredientsTitleTv.text = detailsItem.titleItem
                } else throw IllegalArgumentException("unknown item")
            }
        }
    }

    private class CheckDetailsViewHolder(item: View) : BaseViewHolder(item) {
        val binding = DetailsCheckItemBinding.bind(item)
        override fun bind(detailsItem: DetailsItem) {
            with(binding) {
                if (detailsItem is DetailsItem.CheckDetailsItem) {
                    checkItemCb.text = detailsItem.checkItem
                } else throw IllegalArgumentException("unknown item")
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return when (viewType) {
            HEADER_ITEM_TYPE -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.details_header_item, parent, false)
                HeaderViewHolder(view)
            }

            TITLE_ITEM_TYPE -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.details_title_item, parent, false)
                TitleDetailsViewHolder(view)
            }

            CHECK_ITEM_TYPE -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.details_check_item, parent, false)
                CheckDetailsViewHolder(view)
            }
            else -> throw IllegalArgumentException("Unknown view type")
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.bind(detailsItems[position])
    }

    override fun getItemCount() = detailsItems.size

    override fun getItemViewType(position: Int) = when (detailsItems[position]) {
        is DetailsItem.HeaderDetailsItem -> HEADER_ITEM_TYPE
        is DetailsItem.TitleDetailsItem -> TITLE_ITEM_TYPE
        is DetailsItem.CheckDetailsItem -> CHECK_ITEM_TYPE
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(list: List<DetailsItem>) {
        detailsItems = list
        notifyDataSetChanged()
    }

    companion object {
        private const val HEADER_ITEM_TYPE = 0
        private const val TITLE_ITEM_TYPE = 1
        private const val CHECK_ITEM_TYPE = 2
    }
}
