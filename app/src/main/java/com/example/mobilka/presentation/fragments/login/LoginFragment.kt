package com.example.mobilka.presentation.fragments.login

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.mobilka.R
import com.example.mobilka.app.App
import com.example.mobilka.databinding.FragmentLoginBinding
import com.example.mobilka.model.login.LoginRequest


class LoginFragment : Fragment(R.layout.fragment_login){

    private lateinit var loginViewModel: LoginViewModel
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private var screenMode = MODE_UNKNOWN

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loginViewModel = ViewModelProvider(this)[LoginViewModel::class.java]

        //сюда приходит токен
        loginViewModel.token.observe(viewLifecycleOwner){
            findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
        }
        // открыть фрагмент в режиме входа\регистрации
        launchRightMode()
    }

    // открыть фрагмент в режиме входа\регистрации
    private fun launchRightMode() {
        screenMode = arguments?.getString(SCREEN_MODE).toString()
        when (screenMode) {
            MODE_LOGIN -> launchLoginMode()
            MODE_REGISTRATION -> launchRegistrationMode()
            else -> throw RuntimeException("Unknown screen mode: $screenMode")
        }
    }

    //режим регистрации
    private fun launchRegistrationMode() {
        with(binding){
            etLogin.setText("")
            etPassword.setText("")
            btnLoginIn.text = "Зарегестрироваться"
            tvMode.text = "У меня уже есть аккаунт"
            tvMode.setOnClickListener{
                launchLoginMode()
            }
            //btnLoginIn.setOnClickListener()
        }
    }

    //режим входа
    private fun launchLoginMode() {
        with(binding){
            etLogin.setText("")
            etPassword.setText("")
            btnLoginIn.text = "Войти"
            tvMode.text = "У меня нет аккаунта. Зарегестрироваться!"
            tvMode.setOnClickListener{
                launchRegistrationMode()
            }
            btnLoginIn.setOnClickListener{
                loginViewModel.login((activity?.application as App).coffeeApi,
                    LoginRequest(
                        login = binding.etLogin.text.toString(),
                        password = binding.etPassword.text.toString(),
                    )
                )


                /*loginViewModel.loginWithoutApi(
                    LoginRequest(
                        login = binding.etLogin.text.toString(),
                        password = binding.etPassword.text.toString()
                    )
                )*/

            }
        }
    }



    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object{
        const val SCREEN_MODE = "screen_mode"
        const val MODE_LOGIN = "mode_login"
        const val MODE_REGISTRATION = "mode_registration"
        const val MODE_UNKNOWN = ""

        const val PREF = "pref"
        const val PREF_TOKEN = "pref_token"
        const val PREF_MAIL = "pref_mail"
        const val PREF_LOGIN = "pref_login"
        const val PREF_ICON = "pref_icon"
    }
}
