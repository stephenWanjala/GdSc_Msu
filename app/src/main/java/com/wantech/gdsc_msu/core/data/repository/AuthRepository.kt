package com.wantech.gdsc_msu.core.data.repository

import com.google.firebase.auth.AuthResult

import com.wantech.gdsc_msu.core.util.Resource
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    suspend fun getUserId(): Flow<String>
 suspend fun  getDeferredUserIdAsync(): Deferred<String>
    suspend fun signUpWithEmailAndPassword(
        username: String,
        email: String,
        password: String
    ): Flow<Resource<AuthResult>>

    suspend fun signInWithEmailAndPassword(
        email: String,
        password: String
    ): Flow<Resource<AuthResult>>

    fun isCurrentUserExist(): Flow<Boolean>

    suspend fun getCurrentUserEmail(): Flow<String>
    suspend fun signOut()
}