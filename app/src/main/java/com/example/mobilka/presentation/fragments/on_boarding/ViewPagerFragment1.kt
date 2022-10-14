package com.example.mobilka.presentation.fragments.on_boarding

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.mobilka.R


class ViewPagerFragment1 : Fragment(R.layout.fragment_view_pager1){
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<Button>(R.id.btn_registration).setOnClickListener{
            findNavController().navigate(R.id.action_viewPagerFragment1_to_viewPagerFragment2)
        }
    }
}