package com.wantech.gdsc_msu.feature_auth.login.domain.repositoryImpl

import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.wantech.gdsc_msu.feature_auth.login.data.repository.LoginUserRepository
import com.wantech.gdsc_msu.util.Resource
import com.wantech.gdsc_msu.util.UiText
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(private val auth: FirebaseAuth) :
    LoginUserRepository {
    override suspend fun login(
        email: String,
        password: String
    ): Flow<Resource<AuthResult>> {
        return flow {
            emit(Resource.Loading())
            emit(
                Resource.Success(auth.signInWithEmailAndPassword(email, password).await())
            )

        }.catch {
            emit(
                Resource.Error(UiText.DynamicString(it.message ?: "Unknown Error"))
            )
        }
    }

}