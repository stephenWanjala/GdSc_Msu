package com.wantech.gdsc_msu.feature_auth.sign_up.domain.repositoryImpl

import com.google.firebase.auth.FirebaseAuth
import com.wantech.gdsc_msu.feature_auth.login.data.remote.CreateAccountResponse
import com.wantech.gdsc_msu.feature_auth.sign_up.data.repository.SignUpUserRepository
import com.wantech.gdsc_msu.util.Resource
import com.wantech.gdsc_msu.util.UiText
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class SignUpUserRepositoryImpl
@Inject constructor(private val auth: FirebaseAuth) : SignUpUserRepository {
    override suspend fun register(
        email: String,
        username: String,
        password: String
    ): Flow<Resource<CreateAccountResponse>> {
        try {
            val authResult = auth.createUserWithEmailAndPassword(email, password).await()
            val user = authResult.user
            return flow {
                if (user != null) {
                    emit(Resource.Success(user.email?.let {
                        CreateAccountResponse(
                            it,
                            username,
                            password
                        )
                    }))
                } else {
                    emit(Resource.Error(uiText = UiText.unknownError()))
                }
            }
        } catch (ioException: Exception) {
            return flow {
                emit(
                    Resource.Error(
                        uiText = UiText.DynamicString(
                            ioException.message ?: "Unknown Error"
                        )
                    )
                )
            }
        } catch (httpException: Exception) {
            return flow {
                emit(
                    Resource.Error(
                        uiText = UiText.DynamicString(
                            httpException.message ?: "Unknown Error"
                        )
                    )
                )
            }
        } catch (e: Exception) {
            return flow {
                emit(Resource.Error(UiText.unknownError()))
            }
        }


    }
}