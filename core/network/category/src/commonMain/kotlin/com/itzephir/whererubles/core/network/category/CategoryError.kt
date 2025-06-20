package com.itzephir.whererubles.core.network.category

sealed interface CategoryError {
    sealed interface ReadAllError : CategoryError {
        data object Unauthorized : ReadAllError

        data class Else(val cause: Throwable) : ReadAllError
    }

    sealed interface ReadByTypeError : CategoryError {
        data object WrongParameter : ReadByTypeError

        data object Unauthorized : ReadByTypeError

        data class Else(val cause: Throwable) : ReadByTypeError
    }
}