package com.wantech.gdsc_msu.feature_auth.login.domain.repositoryImpl

import com.google.firebase.auth.FirebaseAuth
import com.wantech.gdsc_msu.feature_auth.login.data.repository.LoginUserRepository
import com.wantech.gdsc_msu.util.SimpleResource
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(private val auth: FirebaseAuth) :
    LoginUserRepository {
    override suspend fun login(email: String, password: String): SimpleResource {
        TODO("Not yet implemented")
    }

}