package com.itzephir.whererubles.core.di

import io.ktor.client.HttpClient
import javax.naming.Context

interface CoreDependencies {
    fun httpClient(): HttpClient

    fun context(): Context
}