package com.example.mobilka.presentation.fragments.coffeelist

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mobilka.app.App
import com.example.mobilka.model.login.LoginRequest
import com.example.mobilka.presentation.fragments.login.LoginFragment
import com.example.mobilka.remote.CoffeeApi
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class CoffeeListViewModel(application: Application): AndroidViewModel(application) {
    private val compositeDisposable = CompositeDisposable()
    private val coffeeList = mutableListOf<CoffeeEntity>()
    private val teaList = mutableListOf<CoffeeEntity>()

    private val _error = MutableLiveData<Unit>()
    val error: LiveData<Unit>
        get() = _error




    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }

    fun getCatalog(coffeeApi: CoffeeApi?){
        coffeeApi?.let{
            compositeDisposable.add(coffeeApi.getCatalog("Bearer " + getToken())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    Log.e("ok", it.toString())
                },{
                    Log.e("err", "error")

                })
            )}
    }

    private fun getToken(): String{
        val sharedPref = getApplication<App>().getSharedPreferences(
            LoginFragment.PREF, Context.MODE_PRIVATE) ?: return ""
        with (sharedPref){
            return getString(LoginFragment.PREF_TOKEN, "").toString()
            }
    }

}