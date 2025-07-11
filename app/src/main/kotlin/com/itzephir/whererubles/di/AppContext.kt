package com.itzephir.whererubles.di

import android.content.Context
import com.itzephir.whererubles.core.navigation.NavigationDependencies
import io.ktor.client.HttpClient
import javax.inject.Inject

class AppContext @Inject constructor(
    override val httpClient: HttpClient,
    override val context: Context,
) : NavigationDependencies
