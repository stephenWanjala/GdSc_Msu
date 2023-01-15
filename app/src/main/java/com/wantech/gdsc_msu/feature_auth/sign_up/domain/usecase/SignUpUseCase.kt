package com.wantech.gdsc_msu.feature_auth.sign_up.domain.usecase

import com.google.firebase.auth.AuthResult
import com.google.firebase.firestore.FirebaseFirestore
import com.wantech.gdsc_msu.core.data.repository.AuthRepository
import com.wantech.gdsc_msu.core.domain.model.Profile
import com.wantech.gdsc_msu.core.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class SignUpUseCase @Inject constructor(
    private val authRepository: AuthRepository,
    private val fireStore: FirebaseFirestore
) {
    suspend operator fun invoke(
        userName: String,
        email: String,
        password: String
    ): Flow<Resource<AuthResult>> {
        var profile: Profile? = null

        val result = authRepository.signUpWithEmailAndPassword(
            username = userName.trim(),
            email = email.trim(),
            password = password.trim()
        )
        val userId: String=authRepository.getDeferredUserIdAsync().await()

        profile = Profile(
            userId = userId, username = userName,
            profilePictureUrl = "", description = "", bio = ""
        )
        fireStore.collection("users").document(userId).set(profile).await()
        return result
    }
}