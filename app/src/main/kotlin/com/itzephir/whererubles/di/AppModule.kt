package com.itzephir.whererubles.di

import com.itzephir.whererubles.app.BuildConfig
import com.itzephir.whererubles.core.connection.ConnectionMonitor
import com.itzephir.whererubles.core.connection.ConnectionStatus
import com.itzephir.whererubles.core.data.common.NetworkProvider
import com.itzephir.whererubles.core.network.provideHttpClient
import dagger.Module
import dagger.Provides
import io.ktor.client.HttpClient

@Module
class AppModule {
    @Provides
    fun httpClient(): HttpClient {
        return provideHttpClient(
            authorizationToken = BuildConfig.apiKey,
        )
    }

    @Provides
    fun networkProvider(connectionMonitor: ConnectionMonitor): NetworkProvider = NetworkProvider {
        println("cool")
        connectionMonitor.getCurrentConnectionStatus() == ConnectionStatus.CONNECTED
    }
}
