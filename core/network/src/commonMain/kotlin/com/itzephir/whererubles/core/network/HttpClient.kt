package com.itzephir.whererubles.core.network

import arrow.resilience.Schedule
import arrow.resilience.ktor.client.HttpRequestSchedule
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.request.bearerAuth
import io.ktor.http.URLProtocol
import io.ktor.http.path
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule
import kotlin.time.Duration.Companion.seconds
import kotlin.time.Instant

fun provideHttpClient(authorizationToken: String): HttpClient =
    HttpClient(engineFactory = CIO) {
        install(plugin = ContentNegotiation) {
            json(json = Json {
                ignoreUnknownKeys = true
                isLenient = true
                explicitNulls = false
                prettyPrint = true
                serializersModule = SerializersModule {
                    contextual(Instant::class, Instant.serializer())
                }
            })
        }

        install(HttpRequestSchedule) {
            fun <A> delay() = Schedule.exponential<A>(2.seconds).jittered()

            retry(delay<Throwable>().and(Schedule.recurs(3)))
        }

        defaultRequest {
            bearerAuth(authorizationToken)
        }

        expectSuccess = true
        followRedirects = true
    }