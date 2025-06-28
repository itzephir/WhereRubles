package com.itzephir.whererubles.domain.usecase

import com.itzephir.whererubles.domain.model.Category
import com.itzephir.whererubles.domain.repository.CategoryRepository

/**
 * Use case to get all categories
 */
class GetCategoriesUseCase(
    private val categoryRepository: CategoryRepository
) {
    suspend operator fun invoke(): List<Category> = categoryRepository.readAll()
}
