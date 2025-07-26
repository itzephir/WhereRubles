package com.itzephir.whererubles.core.network.repository.category

import arrow.core.Either
import com.itzephir.whererubles.core.network.category.CategoryDto
import com.itzephir.whererubles.core.network.category.CategoryError

interface CategoryRepository {
    suspend fun readAll(): Either<CategoryError.ReadAllError, List<CategoryDto>>
    suspend fun readByType(isIncome: Boolean): Either<CategoryError.ReadByTypeError, List<CategoryDto>>
}