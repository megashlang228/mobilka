package com.example.mobilka.data.login.repository

import com.example.mobilka.data.login.remote.api.LoginApi
import com.example.mobilka.domain.common.base.BaseResult
import com.example.mobilka.domain.login.LoginRepository
import com.example.mobilka.domain.login.entity.LoginRequest
import com.example.mobilka.data.login.remote.dto.LoginResponse
import com.example.mobilka.domain.login.entity.LoginEntity
import com.example.mobilka.domain.login.utils.WrappedResponse
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(private val loginApi: LoginApi) : LoginRepository {
    override suspend fun login(loginRequest: LoginRequest): Flow<BaseResult<LoginEntity, WrappedResponse<LoginResponse>>> {
        return flow {
            val response = loginApi.login(loginRequest)
            if(response.isSuccessful){
                val body = response.body()!!
                val loginEntity = LoginEntity(body.data?.login!!, body.data?.mail!!, body.data?.name_icon!!, body.data?.token!!)
                emit(BaseResult.Success(loginEntity))
            }else{
                val type = object : TypeToken<WrappedResponse<LoginResponse>>(){}.type
                val err : WrappedResponse<LoginResponse> = Gson().fromJson(response.errorBody()!!.charStream(), type)
                err.code = response.code()
                emit(BaseResult.Error(err))
            }
        }
    }
}