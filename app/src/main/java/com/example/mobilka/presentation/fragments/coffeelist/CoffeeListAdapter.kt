package com.example.mobilka.presentation.fragments.coffeelist

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class CoffeeListAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {

        val fragment = CoffeeListFragment()
        fragment.arguments = Bundle().apply {
            when(position){
                0 -> putString(CoffeeListFragment.SCREEN_MODE, CoffeeListFragment.MODE_TEA)
                1 -> putString(CoffeeListFragment.SCREEN_MODE, CoffeeListFragment.MODE_COFFEE)
            }

        }
        return fragment
    }
}

