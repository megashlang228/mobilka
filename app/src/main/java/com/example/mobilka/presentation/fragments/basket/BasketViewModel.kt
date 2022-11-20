package com.example.mobilka.presentation.fragments.basket

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mobilka.app.App
import com.example.mobilka.model.BasketModel
import com.example.mobilka.model.login.LoginRequest
import com.example.mobilka.model.login.LoginResponse
import com.example.mobilka.presentation.fragments.coffeelist.ItemFromCatalog
import com.example.mobilka.remote.CoffeeApi
import com.example.mobilka.services.BasketListener
import com.example.mobilka.services.BasketServices
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class BasketViewModel(application: Application): AndroidViewModel(application) {
    private val compositeDisposable = CompositeDisposable()
    private val basketServices: BasketServices
        get() = getApplication<App>().basketServices

    private var listener: BasketListener

    private val _basketList = MutableLiveData<List<BasketModel>>()
    val basketList: LiveData<List<BasketModel>>
        get() = _basketList

    init {
        listener = {
            _basketList.value = it
        }
        basketServices.addListener(listener)
    }

    fun increment(item: String){
        basketServices.incrementItemBasket(item)
    }

    fun decrement(item: String){
        basketServices.decrementItem(item)
    }

    override fun onCleared() {
        compositeDisposable.dispose()
        basketServices.removeListener(listener)
        super.onCleared()
    }


}