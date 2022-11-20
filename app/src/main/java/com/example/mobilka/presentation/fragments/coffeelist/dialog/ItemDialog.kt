package com.example.mobilka.presentation.fragments.coffeelist.dialog

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.example.mobilka.R
import com.example.mobilka.presentation.fragments.coffeelist.ItemFromCatalog
import com.squareup.picasso.Picasso

class ItemDialog(private val item: ItemFromCatalog, private val category: String): DialogFragment() {

    private lateinit var coffeeDialogViewModel: CoffeeDialogViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        dialog!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        return inflater.inflate(R.layout.dialog_item, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val tvCount = dialog!!.findViewById<TextView>(R.id.tv_count_basket)
        coffeeDialogViewModel = ViewModelProvider(this)[CoffeeDialogViewModel::class.java]
        coffeeDialogViewModel.count.observe(viewLifecycleOwner){
            tvCount.text = it.toString()
        }
        dialog!!.findViewById<TextView>(R.id.tv_name_dialog).text = item.item
        dialog!!.findViewById<TextView>(R.id.tv_description_dialog).text = item.item_description
        val img = dialog!!.findViewById<ImageView>(R.id.img_dialog)
        val url = "$URL${category}/${item.icon_name}"
        Picasso.get().load(url).resize(300,300).onlyScaleDown().into(img);

        dialog!!.findViewById<ImageButton>(R.id.btn_increment_basket).setOnClickListener{
            coffeeDialogViewModel.increment(item)
        }
        dialog!!.findViewById<ImageButton>(R.id.btn_decrement_basket).setOnClickListener{
            coffeeDialogViewModel.decrement(item.item)
        }

        coffeeDialogViewModel.getCount(item.item)

    }

    companion object{
        const val URL = "http://192.168.144.64:8080/catalog/getProductIcon/"
    }
}