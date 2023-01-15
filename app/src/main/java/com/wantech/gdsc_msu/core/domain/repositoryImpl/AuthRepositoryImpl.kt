package com.wantech.gdsc_msu.core.domain.repositoryImpl

import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.wantech.gdsc_msu.core.data.repository.AuthRepository
import com.wantech.gdsc_msu.core.domain.model.Profile
import com.wantech.gdsc_msu.core.util.Resource
import com.wantech.gdsc_msu.core.util.UiText
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth
) : AuthRepository {
    val user: Profile? = null
    override suspend fun getUserId(): Flow<String> = flow {
        firebaseAuth.currentUser?.uid?.let {
            emit(it)
        }
    }

    override suspend fun getDeferredUserIdAsync(): Deferred<String> {
        return withContext(Dispatchers.IO) {
            async { firebaseAuth.currentUser?.uid.toString() }
        }
    }


    override suspend fun signUpWithEmailAndPassword(
        username: String,
        email: String,
        password: String
    ): Flow<Resource<AuthResult>> = flow {
        emit(Resource.Loading())
        emit(
            Resource.Success(
                firebaseAuth.createUserWithEmailAndPassword(/* email = */ email, /* password = */
                    password
                ).await()
            )

        )

    }.catch {
        emit(Resource.Error(UiText.DynamicString(it.message ?: "Unknown Error")))
    }

    override suspend fun signInWithEmailAndPassword(
        email: String,
        password: String
    ): Flow<Resource<AuthResult>> {
        return flow {
            emit(Resource.Loading())
            emit(Resource.Success(firebaseAuth.signInWithEmailAndPassword(email, password).await()))
        }.catch {
            emit(Resource.Error(UiText.DynamicString(it.message ?: "Unknown Error")))
        }

    }

    override fun isCurrentUserExist(): Flow<Boolean> = flow {
        emit(firebaseAuth.currentUser != null)
    }


    override suspend fun getCurrentUserEmail(): Flow<String> {
        return flow {
            firebaseAuth.currentUser?.email?.let {
                emit(it)
            }
        }
    }

    override suspend fun signOut() = firebaseAuth.signOut()
}