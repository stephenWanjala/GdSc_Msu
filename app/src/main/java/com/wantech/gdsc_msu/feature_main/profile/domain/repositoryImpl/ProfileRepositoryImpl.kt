package com.wantech.gdsc_msu.feature_main.profile.domain.repositoryImpl

import android.net.Uri
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.wantech.gdsc_msu.core.data.repository.AuthRepository
import com.wantech.gdsc_msu.core.util.Resource
import com.wantech.gdsc_msu.core.util.UiText
import com.wantech.gdsc_msu.feature_main.profile.data.repository.ProfileRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class ProfileRepositoryImpl @Inject constructor(
    private val fireStore: FirebaseFirestore,
    private val authRepository: AuthRepository,
    private val storage: FirebaseStorage
) : ProfileRepository {


    override suspend fun saveProfilePicture(uri: Uri): Flow<Resource<String>> {
        val currentUserId = authRepository.currentUserId
        return flow {
            if (currentUserId !== null) {
                val profilePictureRef = storage.reference.child("profilePictures/$currentUserId")
                val uploadTask = profilePictureRef.putFile(uri)
                try {
                    val taskSnapshot = uploadTask.await()
                    val downloadUrl =
                        taskSnapshot.metadata?.reference?.downloadUrl?.await().toString()
                    storeImageDownloadUrl(downloadUrl)
                    emit(Resource.Success(downloadUrl))
                } catch (e: Exception) {
                    emit(
                        Resource.Error(
                            uiText = UiText.DynamicString(
                                e.message ?: "Unknown Error"
                            )
                        )
                    )
                }

            }
        }
    }


    override suspend fun storeImageDownloadUrl(downloadUrl: String) {
        val currentUserId = authRepository.currentUserId
        if (currentUserId !== null) {
            // Update the user document in Firestore with the profile picture download URL
            val userRef = fireStore.collection("users")
                .document(currentUserId)
            userRef.addSnapshotListener { value, error ->
                if (value?.exists() == true) {
                    userRef.update("profilePicture", downloadUrl).addOnSuccessListener {
                        println("saved successfully")
                    }
                        .addOnFailureListener { exception ->
                            exception.printStackTrace()
                        }

                }
                if (error != null) {
                    fireStore.collection("users").document(currentUserId).set(downloadUrl)
                }
            }

        }
    }

    override fun getProfilePicture(): Flow<Resource<String>> {
        val currentUserId = authRepository.currentUserId
        return flow {
            emit(Resource.Loading())
            if (currentUserId !== null) {
                val userRef = fireStore.collection("users")
                    .document(currentUserId)
                userRef.get().await().let {
                    if (it.exists()) {
                        val profilePicture = it.getString("profilePicture")
                        if (profilePicture != null) {
                            emit(Resource.Success(profilePicture))
                        }
                    }
                }
            }
        }.catch { emit(Resource.Error(UiText.DynamicString(it.message ?: "Unknown Error"))) }
    }
}