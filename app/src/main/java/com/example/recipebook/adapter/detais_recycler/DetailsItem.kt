package com.example.recipebook.adapter.detais_recycler

sealed class DetailsItem {
    data class HeaderDetailsItem(
        var title: String,
        var image: String,
        var servings: Int,
        var readyInMinutes: Int,
    ) : DetailsItem()

    data class TitleDetailsItem(
        var titleItem: String
    ) : DetailsItem()

    data class CheckDetailsItem(
        var checkItem: String
    ) : DetailsItem()
}
