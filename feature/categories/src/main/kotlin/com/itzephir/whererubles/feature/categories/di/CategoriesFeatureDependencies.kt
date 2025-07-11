package com.itzephir.whererubles.feature.categories.di

import io.ktor.client.HttpClient

interface CategoriesFeatureDependencies{
    val httpClient: HttpClient
}