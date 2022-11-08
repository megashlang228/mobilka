package com.example.mobilka.remote

import com.example.mobilka.model.login.LoginRequest
import com.example.mobilka.model.login.LoginResponse
import com.example.mobilka.presentation.fragments.coffeelist.CatalogModel
import io.reactivex.rxjava3.core.Single
import retrofit2.http.*

interface CoffeeApi {

    @POST("login")
    fun login(@Body loginRequest: LoginRequest): Single<LoginResponse>

    @GET("catalog/all")
    fun getCatalog(@Header("Authorization") token: String): Single<CatalogModel>


}