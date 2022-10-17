package com.example.mobilka.data.login.remote.api

import com.example.mobilka.domain.login.entity.LoginRequest
import com.example.mobilka.data.login.remote.dto.LoginResponse
import com.example.mobilka.domain.login.utils.WrappedResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginApi {
    @POST("auth/login")
    suspend fun login(@Body loginRequest: LoginRequest) : Response<WrappedResponse<LoginResponse>>
}