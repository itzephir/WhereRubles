package com.itzephir.whererubles.core.navigation

import android.content.Context
import io.ktor.client.HttpClient


interface NavigationDependencies{
    fun httpClient(): HttpClient

    fun context(): Context
}