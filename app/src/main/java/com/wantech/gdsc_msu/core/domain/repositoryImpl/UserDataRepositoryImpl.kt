package com.wantech.gdsc_msu.core.domain.repositoryImpl

import android.net.Uri
import com.wantech.gdsc_msu.core.data.GdScPreferences
import com.wantech.gdsc_msu.core.data.repository.UserDataRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserDataRepositoryImpl
    (private val gdScPreferences: GdScPreferences) : UserDataRepository {

    override val themeStream: Flow<Int>
        get() = gdScPreferences.getTheme

    override suspend fun setTheme(themeValue: Int) {
        gdScPreferences.saveTheme(themeValue)
    }

    override suspend fun saveProfileUri(uri: String) {
        gdScPreferences.saveProfileUri(uri)
    }

    override fun getProfileUri(): Flow<Uri> {
        return gdScPreferences.getProfileUri().map { uri ->
            Uri.parse(uri)
        }
    }
}