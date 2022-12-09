package com.wantech.gdsc_msu.feature_auth.login.domain.repositoryImpl

import com.google.firebase.auth.FirebaseAuth
import com.wantech.gdsc_msu.feature_auth.login.data.repository.LoginUserRepository
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(private val auth: FirebaseAuth) :
    LoginUserRepository {
    override suspend fun loginUser(email: String, password: String): Boolean {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                val user = task.result.user
            }
            .addOnCanceledListener {
                return@addOnCanceledListener
            }
        return false


    }
}