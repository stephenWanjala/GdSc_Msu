package com.wantech.gdsc_msu.core.data.repository

import kotlinx.coroutines.flow.Flow

interface UserDataRepository {
    val themeStream: Flow<Int>
    suspend fun setTheme(themeValue: Int)
}