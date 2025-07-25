package com.itzephir.whererubles.core.storage.account.preferences

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import com.itzephir.whererubles.core.model.Id
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map

class CurrentAccountPreferences(
    private val datastore: DataStore<Preferences>,
) {
    val currentAccountIdFlow: Flow<Id?> = datastore.data.map { prefs ->
        prefs[CURRENT_ACCOUNT_ID]?.let { Id(it) }
    }

    suspend fun setCurrentId(id: Id) {
        datastore.edit { prefs ->
            prefs[CURRENT_ACCOUNT_ID] = id.value
        }
    }

    suspend fun getCurrentId(): Id? = currentAccountIdFlow.firstOrNull()

    companion object {
        private val CURRENT_ACCOUNT_ID = intPreferencesKey("current_account_id")
    }
}