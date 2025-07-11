package com.itzephir.whererubles.di

import android.content.Context
import com.itzephir.whererubles.core.navigation.NavigationDependencies
import io.ktor.client.HttpClient
import javax.inject.Inject

class AppContext @Inject constructor(
    val httpClient: HttpClient,
    val context: Context,
) : NavigationDependencies {
    override fun httpClient(): HttpClient = httpClient

    override fun context(): Context = context
}