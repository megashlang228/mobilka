package com.example.mobilka.presentation.fragments.on_boarding

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.mobilka.R


class ViewPagerFragment2 : Fragment(R.layout.fragment_view_pager2){
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val btnNext = view.findViewById<Button>(R.id.btn_next2).setOnClickListener{
            findNavController().navigate(R.id.action_viewPagerFragment2_to_viewPagerFragment3)
        }
    }

}