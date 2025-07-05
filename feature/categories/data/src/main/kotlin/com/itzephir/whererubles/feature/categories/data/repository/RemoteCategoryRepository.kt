package com.itzephir.whererubles.feature.categories.data.repository

import arrow.core.Either
import com.itzephir.whererubles.core.network.category.CategoryError
import com.itzephir.whererubles.core.network.category.readCategories
import com.itzephir.whererubles.feature.categories.domain.error.GetAllCategoriesError
import com.itzephir.whererubles.feature.categories.domain.model.Category
import com.itzephir.whererubles.feature.categories.domain.repository.CategoryRepository
import io.ktor.client.HttpClient

class RemoteCategoryRepository(
    private val httpClient: HttpClient,
) : CategoryRepository {
    override suspend fun getAll(): Either<GetAllCategoriesError, List<Category>> =
        httpClient.readCategories()
            .mapLeft(CategoryError.ReadAllError::toGetAllCategoriesError)
            .map(List<com.itzephir.whererubles.core.network.category.Category>::map)
}

