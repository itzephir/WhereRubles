package com.itzephir.whererubles.core.network.transaction

sealed interface TransactionError {

    sealed interface CreateError : TransactionError {
        data object WrongData : CreateError

        data object Unauthorized : CreateError

        data object AccountOrCategoryNotFound : CreateError

        data class Else(val cause: Throwable) : CreateError
    }

    sealed interface ReadByIdError : TransactionError {
        data object WrongId : ReadByIdError

        data object Unauthorized : ReadByIdError

        data object TransactionNotFound : ReadByIdError

        data class Else(val cause: Throwable) : ReadByIdError
    }

    sealed interface UpdateByIdError : TransactionError {
        data object WrongFormat : UpdateByIdError

        data object Unauthorized : UpdateByIdError

        data object TransactionAccountOrCategoryNotFound : UpdateByIdError

        data class Else(val cause: Throwable) : UpdateByIdError
    }

    sealed interface DeleteByIdError : TransactionError {
        data object WrongId : DeleteByIdError

        data object Unauthorized : DeleteByIdError

        data object TransactionNotFound : DeleteByIdError

        data class Else(val cause: Throwable) : DeleteByIdError
    }

    sealed interface ReadByAccountIdAndPeriodError : TransactionError {
        data object WrongFormat : ReadByAccountIdAndPeriodError

        data object Unauthorized : ReadByAccountIdAndPeriodError

        data class Else(val cause: Throwable): ReadByAccountIdAndPeriodError
    }
}