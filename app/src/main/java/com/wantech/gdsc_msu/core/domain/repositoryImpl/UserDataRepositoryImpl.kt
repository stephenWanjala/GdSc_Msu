package com.wantech.gdsc_msu.core.domain.repositoryImpl

import com.wantech.gdsc_msu.core.data.GdScPreferences
import com.wantech.gdsc_msu.core.data.repository.UserDataRepository
import kotlinx.coroutines.flow.Flow

class UserDataRepositoryImpl
    (private val gdScPreferences: GdScPreferences) : UserDataRepository {

    override val themeStream: Flow<Int>
        get() = gdScPreferences.getTheme

    override suspend fun setTheme(themeValue: Int) {
        gdScPreferences.saveTheme(themeValue)
    }
}