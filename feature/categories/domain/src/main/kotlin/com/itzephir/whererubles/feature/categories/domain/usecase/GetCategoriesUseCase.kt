package com.itzephir.whererubles.feature.categories.domain.usecase

import arrow.core.combine
import arrow.core.raise.either
import com.itzephir.whererubles.feature.categories.domain.repository.CategoryRepository

class GetCategoriesUseCase(
    private val categoryRepository: CategoryRepository,
) {
    suspend operator fun invoke() =
        categoryRepository.getAll()
}