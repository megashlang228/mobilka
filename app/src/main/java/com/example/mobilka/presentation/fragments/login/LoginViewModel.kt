package com.example.mobilka.presentation.fragments.login

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mobilka.app.App
import com.example.mobilka.model.login.LoginRequest
import com.example.mobilka.model.login.LoginResponse
import com.example.mobilka.remote.CoffeeApi
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class LoginViewModel(application: Application): AndroidViewModel(application) {
    private val compositeDisposable = CompositeDisposable()
    private var coffeeApi: CoffeeApi = getApplication<App>().coffeeApi

    private val _error = MutableLiveData<Unit>()
    val error: LiveData<Unit>
        get() = _error

    private val _token = MutableLiveData<String>()
    val token: LiveData<String>
        get() = _token


    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }

    //заглушка для входа, пока аpi не работает
    fun loginWithoutApi(loginRequest: LoginRequest){
        if(loginRequest.login == "root" && loginRequest.password == "toor"){
            _token.value = "token"
            saveToken(
                LoginResponse(
                    token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJodHRwOi8vMC4wLjAuMDo4MDgwLyIsImlzcyI6Imh0dHA6Ly8wLjAuMC4wOjgwODAvIiwibG9naW4iOiJyb290In0.GTSOsAV92PeJ0rwyqYsjHDJaj-Q-XdkrO1A08BRE560",
                    mail = "jhzslkflksj@sdk.sd",
                    login = "shdkfjdhksdjh",
                    name_icon = "ijsdjak.png"



                )
            )
        }else{
            _error.value = Unit
        }


    }

    //запрос на сервер для входа
    fun login(loginRequest: LoginRequest){
        coffeeApi.let{
            compositeDisposable.add(coffeeApi.login(loginRequest)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    Log.e("ok", it.toString())
                    saveToken(it)
                    _token.value = it.token
                },{
                    Log.e("err", "error")

                })
            )}
    }

    private fun saveToken(loginResponse: LoginResponse){
        val sharedPref = getApplication<App>().getSharedPreferences(
            LoginFragment.PREF, Context.MODE_PRIVATE) ?: return
        with (sharedPref.edit()){
            putString(LoginFragment.PREF_TOKEN, loginResponse.token)
            putString(LoginFragment.PREF_LOGIN, loginResponse.login)
            putString(LoginFragment.PREF_MAIL, loginResponse.mail)
            putString(LoginFragment.PREF_ICON, loginResponse.name_icon)
            apply()
        }
    }
}