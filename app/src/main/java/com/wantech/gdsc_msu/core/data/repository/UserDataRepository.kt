package com.wantech.gdsc_msu.core.data.repository

import android.net.Uri
import kotlinx.coroutines.flow.Flow

interface UserDataRepository {
    val themeStream: Flow<Int>
    suspend fun setTheme(themeValue: Int)

    suspend fun saveProfileUri(uri: String)
    fun getProfileUri(): Flow<Uri>
}