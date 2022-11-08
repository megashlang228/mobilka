package com.example.mobilka.presentation.fragments.coffeelist

import com.google.gson.annotations.SerializedName

data class CatalogModel(
    @SerializedName("кофе")
    val coffee: List<ItemFromCatalog>,
    @SerializedName("чай")
    val tea: List<ItemFromCatalog>
)

data class ItemFromCatalog(
    val icon_name: String,
    val item: String,
    val item_description: String,
    val price: Int
)