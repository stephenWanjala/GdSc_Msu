package com.wantech.gdsc_msu.feature_auth.sign_up.domain.repositoryImpl

import com.google.firebase.auth.FirebaseAuth
import com.wantech.gdsc_msu.feature_auth.sign_up.data.repository.SignUpUserRepository
import javax.inject.Inject

class SignUpUserRepositoryImpl
    @Inject constructor(private val auth:FirebaseAuth): SignUpUserRepository {
    override suspend fun signUpUser(userName: String, email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    val user = auth.currentUser
                } else {
                    // If sign in fails, display a message to the user.
                    // ...
                }
            }
            .addOnCanceledListener {

            }
    }
}
