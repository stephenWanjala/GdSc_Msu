package com.wantech.gdsc_msu.feature_auth.login.domain.repositoryImpl

import com.google.firebase.auth.FirebaseAuth
import com.wantech.gdsc_msu.feature_auth.login.data.remote.CreateAccountResponse
import com.wantech.gdsc_msu.feature_auth.login.data.repository.LoginUserRepository
import com.wantech.gdsc_msu.util.Resource
import com.wantech.gdsc_msu.util.UiText
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import java.io.IOException
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(private val auth: FirebaseAuth) :
    LoginUserRepository {
    override suspend fun login(
        email: String,
        password: String
    ): Flow<Resource<CreateAccountResponse>> {
        return try {
            val loginResponse = auth.signInWithEmailAndPassword(email, password).await()
            val user = loginResponse.user
            val acountUser = CreateAccountResponse(
                email = user!!.email!!,
                password = password,
                displayName = user.displayName ?: ""

            )
            flow {
                emit(Resource.Success(data = acountUser))
            }
        } catch (io: IOException) {
            flow {
                emit(Resource.Error(uiText = UiText.DynamicString(io.message ?: "Unknown Error")))
            }
        }
    }

}