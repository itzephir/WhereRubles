package com.itzephir.whererubles.di

import com.itzephir.whererubles.app.BuildConfig
import com.itzephir.whererubles.core.network.provideHttpClient
import io.ktor.client.HttpClient
import org.koin.dsl.bind
import org.koin.dsl.module

val appModule = module {
    single {
        provideHttpClient(BuildConfig.apiBaseUrl, BuildConfig.apiKey)
    } bind HttpClient::class
}
