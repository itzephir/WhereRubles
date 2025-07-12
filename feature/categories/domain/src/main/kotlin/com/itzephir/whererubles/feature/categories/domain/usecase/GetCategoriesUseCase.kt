package com.itzephir.whererubles.feature.categories.domain.usecase

import com.itzephir.whererubles.feature.categories.domain.repository.CategoryRepository
import javax.inject.Inject

class GetCategoriesUseCase @Inject constructor(
    private val categoryRepository: CategoryRepository,
) {
    suspend operator fun invoke() =
        categoryRepository.getAll()
}
