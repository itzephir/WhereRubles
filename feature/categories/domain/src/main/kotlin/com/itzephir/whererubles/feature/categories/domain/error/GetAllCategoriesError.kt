package com.itzephir.whererubles.feature.categories.domain.error

sealed interface GetAllCategoriesError {
    data object Unauthorized : GetAllCategoriesError

    data class Else(val cause: Throwable) : GetAllCategoriesError
}
