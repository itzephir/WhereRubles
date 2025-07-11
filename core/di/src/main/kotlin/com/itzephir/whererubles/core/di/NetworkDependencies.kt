package com.itzephir.whererubles.core.di

import io.ktor.client.HttpClient

interface NetworkDependencies {
    val httpClient: HttpClient
}
