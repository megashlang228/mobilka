package com.example.mobilka.domain.login.usecase

import com.example.mobilka.domain.common.base.BaseResult
import com.example.mobilka.domain.login.LoginRepository
import com.example.mobilka.domain.login.entity.LoginRequest
import com.example.mobilka.data.login.remote.dto.LoginResponse
import com.example.mobilka.domain.login.entity.LoginEntity
import com.example.mobilka.domain.login.utils.WrappedResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LoginUseCase @Inject constructor(private val loginRepository: LoginRepository) {
    suspend fun execute(loginRequest: LoginRequest): Flow<BaseResult<LoginEntity, WrappedResponse<LoginResponse>>> {
        return loginRepository.login(loginRequest)
    }

}