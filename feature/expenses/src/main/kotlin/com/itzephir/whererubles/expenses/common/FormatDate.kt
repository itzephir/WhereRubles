package com.itzephir.whererubles.expenses.common

import kotlinx.datetime.Instant
import kotlinx.datetime.format
import kotlinx.datetime.format.DateTimeComponents
import kotlinx.datetime.format.MonthNames
import kotlinx.datetime.format.char

val MonthNames.Companion.RUSSIAN_GENITIVE: MonthNames
    get() = MonthNames(
        listOf(
            "января",
            "февраля",
            "марта",
            "апреля",
            "мая",
            "июня",
            "июля",
            "августа",
            "сентября",
            "октября",
            "ноября",
            "декабря"
        )
    )

fun Instant.formatDate(): String = format(format = DateTimeComponents.Format {
    dayOfMonth()
    char(' ')
    monthName(MonthNames.RUSSIAN_GENITIVE)
    char(' ')
    year()
})
