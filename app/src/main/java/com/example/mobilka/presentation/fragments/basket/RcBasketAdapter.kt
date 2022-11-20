package com.example.mobilka.presentation.fragments.basket

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.mobilka.R
import com.example.mobilka.model.BasketModel
import com.example.mobilka.presentation.fragments.coffeelist.ItemFromCatalog
import com.example.mobilka.presentation.fragments.coffeelist.rcview.RcViewAdapter

class BasketDiffCallback(
    private val oldList: List<BasketModel>,
    private val newList: List<BasketModel>
) : DiffUtil.Callback(){

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldList[oldItemPosition]
        val newItem = newList[newItemPosition]
        return oldItem.item == newItem.item
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldList[oldItemPosition]
        val newItem = newList[newItemPosition]
        return oldItem.count == newItem.count
    }

}

class RcBasketAdapter(): RecyclerView.Adapter<RcBasketAdapter.BasketViewHolder>() {

    var incrementClickListener: ((BasketModel) -> Unit)? = null
    var decrementClickListener: ((BasketModel) -> Unit)? = null

    var list: List<BasketModel> = listOf()
    set(value){
        val diffCallback = BasketDiffCallback(field, value)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        field = value
        diffResult.dispatchUpdatesTo(this)
    }

    class BasketViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val tvName = view.findViewById<TextView>(R.id.tv_name_basket)
        val tvCount = view.findViewById<TextView>(R.id.tv_count_basket)
        val btnIncrement = view.findViewById<ImageButton>(R.id.btn_increment_basket)
        val btnDecrement = view.findViewById<ImageButton>(R.id.btn_decrement_basket)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BasketViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.coffee_item_basket, parent, false)

        return BasketViewHolder(view)
    }

    override fun onBindViewHolder(holder: BasketViewHolder, position: Int) {
        val item = list[position]
        with(holder){
            tvName.text = item.item
            tvCount.text = item.count.toString()
            btnDecrement.setOnClickListener{
                decrementClickListener?.invoke(item)
            }
            btnIncrement.setOnClickListener{
                incrementClickListener?.invoke(item)
            }
        }
    }

    override fun getItemCount(): Int = list.size
}