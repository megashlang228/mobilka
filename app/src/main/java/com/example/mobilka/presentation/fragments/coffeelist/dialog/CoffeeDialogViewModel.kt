package com.example.mobilka.presentation.fragments.coffeelist.dialog

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mobilka.app.App
import com.example.mobilka.model.login.LoginRequest
import com.example.mobilka.presentation.fragments.coffeelist.ItemFromCatalog
import com.example.mobilka.presentation.fragments.login.LoginFragment
import com.example.mobilka.remote.CoffeeApi
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class CoffeeDialogViewModel(application: Application): AndroidViewModel(application) {
    private var basketServices= getApplication<App>().basketServices

    private val _count = MutableLiveData<Int>()
    val count: LiveData<Int>
    get() = _count

    fun increment(item: ItemFromCatalog){
        basketServices.incrementItem(item)
        getCount(item.item)
    }

    fun decrement(item: String){
        basketServices.decrementItem(item)
        getCount(item)
    }

    fun getCount(item: String){
        _count.value = basketServices.getCount(item)
    }

}