package com.itzephir.whererubles.domain.usecase

import com.itzephir.whererubles.domain.model.Category
import com.itzephir.whererubles.domain.repository.CategoryRepository

class GetCategoriesUseCase(
    private val categoryRepository: CategoryRepository
) {
    suspend operator fun invoke(): List<Category> = categoryRepository.readAll()
}