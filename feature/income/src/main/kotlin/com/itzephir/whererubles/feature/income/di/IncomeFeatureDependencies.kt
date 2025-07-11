package com.itzephir.whererubles.feature.income.di

import io.ktor.client.HttpClient

interface IncomeFeatureDependencies {
    val httpClient: HttpClient
}