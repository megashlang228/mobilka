package com.example.mobilka.presentation.fragments.coffeelist.rcview


import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mobilka.R
import com.example.mobilka.model.login.LoginResponse
import com.example.mobilka.presentation.fragments.coffeelist.ItemFromCatalog
import com.squareup.picasso.Picasso


class RcViewAdapter(private val category: String):RecyclerView.Adapter<RcViewAdapter.ViewHolder>() {

    var onItemClickListener: ((ItemFromCatalog) -> Unit)? = null
    private val list = mutableListOf<ItemFromCatalog>()

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imgView: ImageView = view.findViewById(R.id.img_coffee)
        val tvCost: TextView = view.findViewById(R.id.tv_cost)
        val tvName: TextView = view.findViewById(R.id.tv_name)


    }


    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.coffee_item, viewGroup, false)

        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val item = list[position]
        viewHolder.tvName.text = item.item
        viewHolder.tvCost.text = item.price.toString()
        viewHolder.itemView.setOnClickListener{
            onItemClickListener?.invoke(item)
        }
        val url = "${URL}${category}/${item.icon_name}"
        Log.e("url", url)

        Picasso.get().load(url).resize(300,300).onlyScaleDown().into(viewHolder.imgView);

    }

    override fun getItemCount() = list.size

    fun updateAdapter(newList: List<ItemFromCatalog>){
        // обновляем список
        list.clear()
        list.addAll(newList)
        notifyDataSetChanged()
    }

    companion object{
        const val URL = "http://192.168.144.64:8080/catalog/getProductIcon/"
    }

}
