package com.example.mobilka.services

import android.util.Log
import com.example.mobilka.model.BasketModel
import com.example.mobilka.presentation.fragments.coffeelist.CatalogModel
import com.example.mobilka.presentation.fragments.coffeelist.ItemFromCatalog

typealias BasketListener = (list: List<BasketModel>) -> Unit

class BasketServices {
    private var list = mutableListOf<BasketModel>()
    private val listeners = mutableListOf<BasketListener>()

    fun getBasket(): List<BasketModel> {
        return list
    }

    fun addListener(listener: BasketListener) {
        listeners.add(listener)
        listener.invoke(list)
    }



    fun incrementItem(item: ItemFromCatalog) {
        val copy = mutableListOf<BasketModel>()
        copy.addAll(list)
        list = copy
        val oldIndex = list.indexOfFirst { it.item == item.item }
        if (oldIndex != -1) {
            val updatedElement = list[oldIndex].copy(count = list[oldIndex].count + 1)
            list[oldIndex] = updatedElement
            }
        else{
            list.add(
                BasketModel(
                    item = item.item,
                    count = 1,
                    price = item.price
                )
            )
        }
        notifyChanges()
    }

    fun incrementItemBasket(item: String) {
        val oldIndex = list.indexOfFirst { it.item == item }
        if (oldIndex != -1) {
            val copy = mutableListOf<BasketModel>()
            copy.addAll(list)
            list = copy
            val updatedElement = list[oldIndex].copy(count = list[oldIndex].count + 1)
            list[oldIndex] = updatedElement
            notifyChanges()
        }
    }

    fun decrementItem(item: String) {
        val oldIndex = list.indexOfFirst { it.item == item }
            if (oldIndex != -1) {
                val copy = mutableListOf<BasketModel>()
                copy.addAll(list)
                list = copy
                val updatedElement = list[oldIndex].copy(count = list[oldIndex].count - 1)
                list[oldIndex] = updatedElement
                if (updatedElement.count == 0) {
                    list.remove(updatedElement)
                }
                notifyChanges()
            }
    }

    fun getCount(item: String): Int {
        for (element in list) {
            if (element.item == item) {
                return element.count
            }
        }
        return 0
    }


    fun removeListener(listener: BasketListener) {
        listeners.remove(listener)
    }

    fun notifyChanges(){
        listeners.forEach{it.invoke(list)}
    }
}