package com.example.mobilka.app

import android.app.Application
import com.example.mobilka.remote.CoffeeApi
import com.example.mobilka.services.BasketServices
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


class App: Application() {

    val basketServices = BasketServices()

    lateinit var coffeeApi: CoffeeApi

    override fun onCreate() {
        super.onCreate()

        configureRetrofit()
    }


    private fun configureRetrofit(){
        val httpLoginInterceptor = HttpLoggingInterceptor()
        httpLoginInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(httpLoginInterceptor)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()

        coffeeApi = retrofit.create(CoffeeApi::class.java)
    }

    companion object{
        const val URL = "http://192.168.144.64:8080/"
    }
}