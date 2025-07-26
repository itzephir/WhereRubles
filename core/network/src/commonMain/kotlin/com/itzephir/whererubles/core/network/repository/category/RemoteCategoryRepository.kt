package com.itzephir.whererubles.core.network.repository.category

import arrow.core.Either
import com.itzephir.whererubles.core.network.category.CategoryDto
import com.itzephir.whererubles.core.network.category.CategoryError
import com.itzephir.whererubles.core.network.category.readCategories
import com.itzephir.whererubles.core.network.category.readCategoriesByType
import io.ktor.client.HttpClient

class RemoteCategoryRepository(
    private val httpClient: HttpClient,
): CategoryRepository {
    override suspend fun readAll(): Either<CategoryError.ReadAllError, List<CategoryDto>> =
        httpClient.readCategories()

    override suspend fun readByType(isIncome: Boolean): Either<CategoryError.ReadByTypeError, List<CategoryDto>> =
        httpClient.readCategoriesByType(isIncome)
}