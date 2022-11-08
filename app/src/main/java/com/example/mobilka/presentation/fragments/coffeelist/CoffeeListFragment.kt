package com.example.mobilka.presentation.fragments.coffeelist

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.mobilka.R
import com.example.mobilka.app.App
import com.example.mobilka.databinding.FragmentCoffeeListBinding
import com.example.mobilka.databinding.FragmentLoginBinding
import com.example.mobilka.model.login.LoginRequest
import com.example.mobilka.presentation.fragments.login.LoginViewModel

class CoffeeListFragment : Fragment(R.layout.fragment_coffee_list){

    private lateinit var coffeeListViewModel: CoffeeListViewModel
    private var _binding: FragmentCoffeeListBinding? = null
    private val binding get() = _binding!!

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
                MODE_TEA -> Log.e("sdf", "tea")
                MODE_COFFEE ->  Log.e("sdf", "coffee")
            }
        }
        coffeeListViewModel.getCatalog((activity?.application as App).coffeeApi)
    }

    companion object{
        const val SCREEN_MODE = "screen_mode"
        const val MODE_TEA = "mode_tea"
        const val MODE_COFFEE = "mode_coffee"
    }
}