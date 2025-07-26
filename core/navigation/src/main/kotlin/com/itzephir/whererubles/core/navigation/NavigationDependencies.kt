package com.itzephir.whererubles.core.navigation

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.itzephir.whererubles.core.data.common.NetworkProvider
import com.itzephir.whererubles.core.storage.account.preferences.CurrentAccountPreferences
import io.ktor.client.HttpClient


interface NavigationDependencies {
    val httpClient: HttpClient

    val context: Context

    val networkProvider: NetworkProvider

    val currentAccountPreferences: CurrentAccountPreferences
}
