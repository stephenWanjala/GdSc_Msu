package com.wantech.gdsc_msu.feature_auth.sign_up.domain.usecase

import com.wantech.gdsc_msu.feature_auth.sign_up.data.repository.SignUpUserRepository
import javax.inject.Inject

class SignUpUseCase @Inject constructor(
   private  val  repository: SignUpUserRepository
) {
    suspend operator  fun invoke(userName:String,email: String, password: String) {
        return repository.signUpUser(email, password, userName)
    }
}