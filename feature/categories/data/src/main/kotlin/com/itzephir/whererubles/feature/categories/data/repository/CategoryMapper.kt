package com.itzephir.whererubles.feature.categories.data.repository

import com.itzephir.whererubles.core.network.category.Category
import com.itzephir.whererubles.core.network.category.CategoryError
import com.itzephir.whererubles.feature.categories.domain.error.GetAllCategoriesError

fun CategoryError.ReadAllError.toGetAllCategoriesError(): GetAllCategoriesError =
    when (this) {
        is CategoryError.ReadAllError.Unauthorized -> GetAllCategoriesError.Unauthorized
        is CategoryError.ReadAllError.Else         -> GetAllCategoriesError.Else(cause)
    }

internal fun Category.map(): com.itzephir.whererubles.feature.categories.domain.model.Category =
    com.itzephir.whererubles.feature.categories.domain.model.Category(
        id = com.itzephir.whererubles.feature.categories.domain.model.Category.CategoryId(id.value),
        name = name,
        emoji = emoji,
        isIncome = isIncome,
    )

internal fun List<Category>.map(): List<com.itzephir.whererubles.feature.categories.domain.model.Category> =
    map(Category::map)