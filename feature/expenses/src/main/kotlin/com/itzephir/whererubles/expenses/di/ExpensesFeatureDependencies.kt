package com.itzephir.whererubles.expenses.di

import io.ktor.client.HttpClient

interface ExpensesFeatureDependencies{
    val httpClient: HttpClient
}