package com.example.mobilka.presentation.fragments.profile

import android.app.Application
import android.content.Context
import android.graphics.Bitmap
import android.util.Log
import android.widget.Toast
import androidx.core.content.edit
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.navigation.fragment.findNavController
import com.example.mobilka.R
import com.example.mobilka.app.App
import com.example.mobilka.model.login.LoginRequest
import com.example.mobilka.presentation.fragments.login.LoginFragment
import com.example.mobilka.remote.CoffeeApi
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class ProfileViewModel(application: Application): AndroidViewModel(application) {
    private val compositeDisposable = CompositeDisposable()


    private val _login = MutableLiveData<String>()
    val login: LiveData<String>
        get() = _login

    private val _mail = MutableLiveData<String>()
    val mail: LiveData<String>
        get() = _mail

    private val _icon = MutableLiveData<Bitmap>()
    val icon: LiveData<Bitmap>
        get() = _icon


    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }

    fun getPref(){
        val sharedPref = getApplication<App>().getSharedPreferences(
            LoginFragment.PREF, Context.MODE_PRIVATE) ?: return
        with (sharedPref){
            _login.value = getString(LoginFragment.PREF_LOGIN, "")
            _mail.value = getString(LoginFragment.PREF_MAIL, "")
//            _icon.value = getIcon(getString(LoginFragment.PREF_ICON, ""))
        }
    }

//    private fun getIcon(string: String?): Bitmap {
//
//    }

    fun logout(){
        val sharedPref = getApplication<App>().getSharedPreferences(
            LoginFragment.PREF, Context.MODE_PRIVATE) ?: return
        sharedPref.edit().clear().apply()
    }

}