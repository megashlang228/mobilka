package com.example.mobilka.presentation.fragments.coffeelist

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mobilka.R
import com.example.mobilka.databinding.FragmentCoffeeListBinding
import com.example.mobilka.presentation.fragments.coffeelist.dialog.ItemDialog
import com.example.mobilka.presentation.fragments.coffeelist.rcview.RcViewAdapter

class CoffeeListFragment : Fragment(R.layout.fragment_coffee_list){

    private lateinit var coffeeListViewModel: CoffeeListViewModel
    private var _binding: FragmentCoffeeListBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: RcViewAdapter
    private var screenMode = ""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCoffeeListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        coffeeListViewModel = ViewModelProvider(this)[CoffeeListViewModel::class.java]

        arguments?.takeIf { it.containsKey(SCREEN_MODE) }?.apply {
            when(getString(SCREEN_MODE)){
                MODE_TEA -> {
                    screenMode = "чай"
                    coffeeListViewModel.catalogTea.observe(viewLifecycleOwner){
                        adapter.updateAdapter(it)
                    }
                }
                MODE_COFFEE -> {
                    screenMode = "кофе"
                    coffeeListViewModel.catalogCoffee.observe(viewLifecycleOwner){
                        adapter.updateAdapter(it)
                    }
                }
            }
        }
        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        adapter = RcViewAdapter(screenMode)
        recyclerView.adapter = adapter
        adapter.onItemClickListener = {
            Log.d("aaa", "name: ${it.item}, count: ${it.item_description}, status: ${it.price}")
            ItemDialog(it, screenMode).show(parentFragmentManager, "")

        }
        coffeeListViewModel.getCatalog()
    }

    companion object{
        const val SCREEN_MODE = "screen_mode"
        const val MODE_TEA = "mode_tea"
        const val MODE_COFFEE = "mode_coffee"
    }
}