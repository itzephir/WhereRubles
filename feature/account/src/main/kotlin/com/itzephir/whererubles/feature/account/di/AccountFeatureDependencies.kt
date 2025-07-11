package com.itzephir.whererubles.feature.account.di

import android.content.Context
import io.ktor.client.HttpClient

interface AccountFeatureDependencies {
    fun httpClient(): HttpClient
    fun context(): Context
}