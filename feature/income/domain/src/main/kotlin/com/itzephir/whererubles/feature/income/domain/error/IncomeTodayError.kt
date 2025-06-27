package com.itzephir.whererubles.feature.income.domain.error

sealed interface IncomeTodayError {
    data object WrongFormat: IncomeTodayError

    data object Unauthorized: IncomeTodayError

    data object NoAccount: IncomeTodayError

    data class Else(val cause: Throwable): IncomeTodayError
}
