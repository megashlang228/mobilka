package com.example.mobilka.presentation.fragments.coffeelist

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.mobilka.R
import com.example.mobilka.presentation.fragments.coffeelist.CoffeeListAdapter
import com.example.mobilka.presentation.fragments.login.LoginFragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class TeaListFragment : Fragment(R.layout.fragment_tea_list){

    private val tabTitles = listOf("чай", "кофе")
    private var token: String? = null
    private lateinit var coffeeListAdapter: CoffeeListAdapter
    private lateinit var viewPager: ViewPager2
    private lateinit var tabLayout: TabLayout

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        coffeeListAdapter = CoffeeListAdapter(this)
        viewPager = view.findViewById(R.id.vp2_coffe)
        viewPager.adapter = coffeeListAdapter
        tabLayout = view.findViewById(R.id.tl_coffe)
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = tabTitles[position]
        }.attach()

    }


}