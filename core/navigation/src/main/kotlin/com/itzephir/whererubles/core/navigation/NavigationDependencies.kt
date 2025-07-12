package com.itzephir.whererubles.core.navigation

import android.content.Context
import io.ktor.client.HttpClient


interface NavigationDependencies {
    val httpClient: HttpClient

    val context: Context
}
