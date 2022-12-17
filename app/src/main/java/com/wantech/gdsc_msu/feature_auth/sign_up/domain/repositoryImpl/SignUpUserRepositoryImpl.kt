package com.wantech.gdsc_msu.feature_auth.sign_up.domain.repositoryImpl

import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.wantech.gdsc_msu.feature_auth.sign_up.data.repository.SignUpUserRepository
import com.wantech.gdsc_msu.util.Resource
import com.wantech.gdsc_msu.util.UiText
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class SignUpUserRepositoryImpl
@Inject constructor(private val auth: FirebaseAuth) : SignUpUserRepository {
    override suspend fun register(
        email: String,
        username: String,
        password: String
    ): Flow<Resource<AuthResult>> {
        return flow {
            emit(Resource.Loading())
            emit(
                Resource.Success(
                    auth.createUserWithEmailAndPassword(email, password).await()
                )
            )

        }.catch {
            emit(Resource.Error(UiText.DynamicString(it.message ?: "Unknown Error")))
        }


    }
}