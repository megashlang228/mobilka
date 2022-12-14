package com.example.mobilka.presentation.fragments.coffeelist

import com.google.gson.annotations.SerializedName

data class CatalogModel(
    @SerializedName("кофе")
    val coffee: MutableList<ItemFromCatalog>,
    @SerializedName("чай")
    val tea: MutableList<ItemFromCatalog>
)

data class ItemFromCatalog(
    val icon_name: String = "",
    val item: String = "",
    val item_description: String = "",
    val price: Int = 0
)