package com.wantech.gdsc_msu.core.data

import androidx.appcompat.app.AppCompatDelegate
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GdScPreferences(private val datastore: DataStore<Preferences>) {
    companion object {
        val THEME_OPTIONS = intPreferencesKey(name = "theme_option")
        val USER_DATA = intPreferencesKey(name = "user_data")
        const val GDSC_PREFERENCES = "gdsc_preference"
    }

    suspend fun saveTheme(themeValue: Int) {
        datastore.edit { preference ->
            preference[THEME_OPTIONS] = themeValue
        }
    }

    val getTheme: Flow<Int> = datastore.data.map { preferences ->
        preferences[THEME_OPTIONS] ?: AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
    }
}