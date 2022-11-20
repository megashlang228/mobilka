package com.example.mobilka.presentation.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.mobilka.R
import com.example.mobilka.app.App
import com.example.mobilka.presentation.fragments.login.LoginFragment
import com.example.mobilka.presentation.fragments.login.LoginFragment.Companion


class SplashFragment : Fragment(R.layout.fragment_splash){
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if(isPref()){
            view.postDelayed({
                findNavController().navigate(R.id.action_splashFragment_to_homeFragment)
            }, 2000)
        }else{
            view.postDelayed({
                findNavController().navigate(R.id.action_splashFragment_to_viewPagerFragment1)
            }, 2000)
        }




    }
    private  fun isPref(): Boolean{
        val sharedPref = requireContext().applicationContext.getSharedPreferences(
            LoginFragment.PREF, Context.MODE_PRIVATE)

        return sharedPref.contains(LoginFragment.PREF_TOKEN)

    }
}