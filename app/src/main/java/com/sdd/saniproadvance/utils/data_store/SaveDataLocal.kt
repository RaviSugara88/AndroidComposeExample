package com.sdd.saniproadvance.utils.data_store

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class StoreData(private val context: Context) {

    // to make sure there's only one instance
    companion object {
        private val Context.dataStoree: DataStore<Preferences> by preferencesDataStore("userData")
        val USER_LOGIN_KEY = booleanPreferencesKey("is_login")
    }

    //get the saved email
    val getLoginStatus: Flow<Boolean?> = context.dataStoree.data
        .map { preferences ->
            preferences[USER_LOGIN_KEY] ?: false
        }

    //save email into datastore
    suspend fun saveLoginStatus(status: Boolean) {
        context.dataStoree.edit { preferences ->
            preferences[USER_LOGIN_KEY] = status
        }
    }


}