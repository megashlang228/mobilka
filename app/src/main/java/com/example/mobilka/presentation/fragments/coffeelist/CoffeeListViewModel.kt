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
    private var coffeeApi: CoffeeApi = getApplication<App>().coffeeApi
    private val compositeDisposable = CompositeDisposable()
    private val coffeeList = mutableListOf<ItemFromCatalog>()
    private val teaList = mutableListOf<ItemFromCatalog>()

    private val _catalogCoffee = MutableLiveData<MutableList<ItemFromCatalog>>()
    val catalogCoffee: LiveData<MutableList<ItemFromCatalog>>
        get() = _catalogCoffee

    private val _catalogTea = MutableLiveData<MutableList<ItemFromCatalog>>()
    val catalogTea: LiveData<MutableList<ItemFromCatalog>>
        get() = _catalogTea




    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }

//    fun getCatalog(){
//        coffeeApi.let{
//            compositeDisposable.add(coffeeApi.getCatalog("Bearer " + getToken())
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe({
//                    _catalogCoffee.value = it.coffee
//                    _catalogTea.value = it.tea
//                },{
//                    Log.e("err", "error")
//
//                })
//            )}
//    }

    private fun getToken(): String{
        val sharedPref = getApplication<App>().getSharedPreferences(
            LoginFragment.PREF, Context.MODE_PRIVATE) ?: return ""
        with (sharedPref){
            return getString(LoginFragment.PREF_TOKEN, "").toString()
            }
    }

    fun getCatalog(){
        coffeeList.add(ItemFromCatalog(item = "Экспрессо",price = 120, item_description = "Самый молодой, самый современный, самый быстрый способ приготовления кофе. Недаром на своей родине, в Италии, эспрессо считают «кофейным королем»."))
        coffeeList.add(ItemFromCatalog(item = "Капучино",price = 150, item_description = "это кофе, который состоит из одной части эспрессо, одной части подогретого молока и одной части молочной пены."))
        coffeeList.add(ItemFromCatalog(item = "Американо",price = 110, item_description = "то кофе эспрессо, дополнительно разбавленный водой. Рецепт и название напиток получил как пренебрежительное название «не настоящего» эспрессо, придуманное итальянцами"))
        coffeeList.add(ItemFromCatalog(item = "Латте",price = 90, item_description = "кофе готовят на основе эспрессо, мокаччино, американо с добавлением молока."))
        coffeeList.add(ItemFromCatalog(item = "Фраппе",price = 160, item_description = "покрытый молочной пеной холодный кофейный напиток греческого происхождения."))
        coffeeList.add(ItemFromCatalog(item = "Кофе с льдом",price = 90, item_description = "кофе с добавлением льда"))

        _catalogCoffee.value = coffeeList

        teaList.add(ItemFromCatalog(item = "Белый",price = 90, item_description = "чай созданный из почек и самых молодых побегов чайного куста"))
        teaList.add(ItemFromCatalog(item = "Зеленный",price = 80, item_description = "Он ценится гораздо выше других разновидностей. Все благодаря поразительной пользе и более тонкому эффекту"))
        teaList.add(ItemFromCatalog(item = "Желтый",price = 110, item_description = "Тонкие ноты проявляются благодаря дополнительному этапу обработки чайного листа. Он называется томлением или обертыванием."))
        teaList.add(ItemFromCatalog(item = "Улун",price = 90, item_description = "самая ароматная разновидность чая. Недаром сорта этой группы являются любимцами чайных церемоний, в которых ценится многообразие оттенков и нот."))
        teaList.add(ItemFromCatalog(item = "Черный",price = 90, item_description = "Его приятный мягкий вкус радует миллионы людей во всем мире."))
        teaList.add(ItemFromCatalog(item = "Пуэр",price = 100, item_description = "это уникальный напиток, ценность которого увеличивается с возрастом. Чем дольше он будет храниться, тем лучше и изысканнее будет его вкус."))

        _catalogTea.value = teaList
    }

}