package com.itzephir.whererubles.core.network.account

sealed interface AccountError {
    sealed interface ReadAllError : AccountError {
        data object Unauthorized : ReadAllError

        data class Else(val cause: Throwable) : ReadAllError
    }

    sealed interface CreateError : AccountError {
        data object WrongData : CreateError

        data object Unauthorized : CreateError

        data class Else(val cause: Throwable) : CreateError
    }

    sealed interface ReadByIdError : AccountError {
        data object WrongId : ReadByIdError

        data object Unauthorized : ReadByIdError

        data object NotFound : ReadByIdError

        data class Else(val cause: Throwable) : ReadByIdError
    }

    sealed interface UpdateByIdError : AccountError {
        data object WrongData : UpdateByIdError

        data object Unauthorized : UpdateByIdError

        data object NotFound : UpdateByIdError

        data class Else(val cause: Throwable) : UpdateByIdError
    }

    sealed interface DeleteByIdError : AccountError {
        data object WrongData : DeleteByIdError

        data object Unauthorized : DeleteByIdError

        data object NotFound : DeleteByIdError

        data object NotCleared : DeleteByIdError

        data class Else(val cause: Throwable) : DeleteByIdError
    }

    sealed interface ReadAccountHistoryByIdError : AccountError {
        data object WrongId : ReadAccountHistoryByIdError

        data object Unauthorized : ReadAccountHistoryByIdError

        data object NotFound : ReadAccountHistoryByIdError

        data class Else(val cause: Throwable) : ReadAccountHistoryByIdError
    }
}