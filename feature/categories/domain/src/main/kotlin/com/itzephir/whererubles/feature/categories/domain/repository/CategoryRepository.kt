package com.itzephir.whererubles.feature.categories.domain.repository

import arrow.core.Either
import com.itzephir.whererubles.feature.categories.domain.error.GetAllCategoriesError
import com.itzephir.whererubles.feature.categories.domain.model.Category

interface CategoryRepository {
    suspend fun getAll(): Either<GetAllCategoriesError, List<Category>>
}