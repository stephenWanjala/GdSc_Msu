package com.wantech.gdsc_msu.feature_auth.sign_up.domain.usecase

import com.wantech.gdsc_msu.feature_auth.login.data.remote.CreateAccountResponse
import com.wantech.gdsc_msu.feature_auth.sign_up.data.repository.SignUpUserRepository
import com.wantech.gdsc_msu.util.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SignUpUseCase @Inject constructor(
   private  val  repository: SignUpUserRepository
) {
    suspend operator  fun invoke(userName:String,email: String, password: String): Flow<Resource<CreateAccountResponse>> {
        return repository.register(email, password, userName)
    }
}