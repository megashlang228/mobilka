package com.example.mobilka.presentation.fragments

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.mobilka.R


class WelcomeFragment : Fragment(R.layout.fragment_welcome){
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<Button>(R.id.btn_login).setOnClickListener{
            findNavController().navigate(
                R.id.action_welcomeFragment_to_loginFragment,
                bundleOf(LoginFragment.SCREEN_MODE to LoginFragment.MODE_LOGIN)
            )
        }
        view.findViewById<Button>(R.id.btn_registration).setOnClickListener{
            findNavController().navigate(
                R.id.action_welcomeFragment_to_loginFragment,
                bundleOf(LoginFragment.SCREEN_MODE to LoginFragment.MODE_REGISTRATION)
            )
        }
    }

}