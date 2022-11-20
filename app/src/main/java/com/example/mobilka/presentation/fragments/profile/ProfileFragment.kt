package com.example.mobilka.presentation.fragments.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.example.mobilka.R
import com.example.mobilka.databinding.FragmentLoginBinding
import com.example.mobilka.databinding.FragmentProfileBinding
import com.example.mobilka.presentation.fragments.login.LoginFragment
import com.example.mobilka.presentation.fragments.login.LoginViewModel


class ProfileFragment : Fragment(R.layout.fragment_profile){
    private lateinit var profileViewModel: ProfileViewModel
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        profileViewModel = ViewModelProvider(this)[ProfileViewModel::class.java]
        profileViewModel.login.observe(viewLifecycleOwner){
            binding.tvLogin.text = it
        }
        profileViewModel.mail.observe(viewLifecycleOwner){
            binding.btnMail.text = it
        }
        profileViewModel.getPref()
        binding.btnLogout.setOnClickListener{
            profileViewModel.logout()
            Navigation.findNavController(requireActivity(), R.id.ContainerView)
                .navigate(R.id.action_homeFragment_to_welcomeFragment)

        }
    }
}