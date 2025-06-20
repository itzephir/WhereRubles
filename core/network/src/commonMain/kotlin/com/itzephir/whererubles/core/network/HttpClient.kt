package com.itzephir.whererubles.core.network

import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.request.bearerAuth
import io.ktor.http.URLProtocol
import io.ktor.http.path
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

fun provideHttpClient(baseUrl: String, authorizationToken: String) = HttpClient(engineFactory = CIO) {
    install(plugin = ContentNegotiation) {
        json(json = Json {
            ignoreUnknownKeys = true
            isLenient = true
            explicitNulls = false
            prettyPrint = true
        })
    }

    defaultRequest {
        url{
            protocol = URLProtocol.HTTPS
            host = baseUrl
            path("api/", "v1/")
        }
        bearerAuth(authorizationToken)
    }

    expectSuccess = true
    followRedirects = true
}