package com.itzephir.whererubles.feature.categories.di.categories

import com.itzephir.whererubles.feature.categories.domain.usecase.GetCategoriesUseCase

interface CategoriesDependencies {
    val getCategoriesUseCase: GetCategoriesUseCase
}
