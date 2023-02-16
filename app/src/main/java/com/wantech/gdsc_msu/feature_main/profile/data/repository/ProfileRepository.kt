package com.wantech.gdsc_msu.feature_main.profile.data.repository


import android.net.Uri
import com.wantech.gdsc_msu.core.util.Resource
import kotlinx.coroutines.flow.Flow

interface ProfileRepository {

    suspend fun saveProfilePicture(uri: Uri): Flow<Resource<String>>
    suspend fun storeImageDownloadUrl(downloadUrl: String)
     fun getProfilePicture(): Flow<Resource<String>>
}