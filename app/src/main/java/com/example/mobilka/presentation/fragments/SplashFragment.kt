package com.example.mobilka.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.mobilka.R


class SplashFragment : Fragment(R.layout.fragment_splash){
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.postDelayed({
            findNavController().navigate(R.id.action_splashFragment_to_viewPagerFragment1)
        }, 2000)


    }
}