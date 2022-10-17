package com.example.mobilka.domain.login

import com.example.mobilka.domain.common.base.BaseResult
import com.example.mobilka.domain.login.entity.LoginRequest
import com.example.mobilka.data.login.remote.dto.LoginResponse
import com.example.mobilka.domain.login.entity.LoginEntity
import com.example.mobilka.domain.login.utils.WrappedResponse
import kotlinx.coroutines.flow.Flow


interface LoginRepository {
    suspend fun login(loginRequest: LoginRequest) : Flow<BaseResult<LoginEntity, WrappedResponse<LoginResponse>>>
}