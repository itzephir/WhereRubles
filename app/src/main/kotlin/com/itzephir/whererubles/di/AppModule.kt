package com.itzephir.whererubles.di

import com.itzephir.whererubles.app.BuildConfig
import com.itzephir.whererubles.core.network.provideHttpClient
import dagger.Module
import dagger.Provides
import io.ktor.client.HttpClient

@Module
class AppModule {
    @Provides
    fun httpClient(): HttpClient {
        return provideHttpClient(
            baseUrl = BuildConfig.apiBaseUrl,
            authorizationToken = BuildConfig.apiKey,
        )
    }
}