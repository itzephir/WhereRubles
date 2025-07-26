package com.itzephir.whererubles.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.itzephir.whererubles.core.data.common.NetworkProvider
import com.itzephir.whererubles.core.navigation.NavigationDependencies
import com.itzephir.whererubles.core.storage.account.preferences.CurrentAccountPreferences
import io.ktor.client.HttpClient
import javax.inject.Inject

class AppContext @Inject constructor(
    override val httpClient: HttpClient,
    override val context: Context,
    override val networkProvider: NetworkProvider,
    override val currentAccountPreferences: CurrentAccountPreferences,
) : NavigationDependencies
