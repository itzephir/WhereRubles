package com.itzephir.whererubles.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.datastore.preferences.preferencesDataStoreFile
import com.itzephir.whererubles.app.BuildConfig
import com.itzephir.whererubles.core.connection.ConnectionMonitor
import com.itzephir.whererubles.core.connection.ConnectionStatus
import com.itzephir.whererubles.core.data.common.NetworkProvider
import com.itzephir.whererubles.core.network.provideHttpClient
import com.itzephir.whererubles.core.storage.account.preferences.CurrentAccountPreferences
import dagger.Module
import dagger.Provides
import io.ktor.client.HttpClient
import io.ktor.util.SilentSupervisor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import javax.inject.Singleton

private val Context.dataStore by preferencesDataStore("prefs")

@Module
class AppModule {
    @Provides
    @Singleton
    fun httpClient(): HttpClient {
        return provideHttpClient(
            authorizationToken = BuildConfig.apiKey,
        )
    }

    @Provides
    @Singleton
    fun networkProvider(connectionMonitor: ConnectionMonitor): NetworkProvider = NetworkProvider {
        connectionMonitor.getCurrentConnectionStatus() == ConnectionStatus.CONNECTED
    }

    @Provides
    @Singleton
    fun dataStore(context: Context): DataStore<Preferences> {
        return context.dataStore
    }

    @Provides
    @Singleton
    fun currentAccountPreferences(dataStore: DataStore<Preferences>) =
        CurrentAccountPreferences(dataStore)
}
