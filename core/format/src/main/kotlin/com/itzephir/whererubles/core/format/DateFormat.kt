package com.itzephir.whererubles.core.format

import kotlinx.datetime.TimeZone
import kotlinx.datetime.format
import kotlinx.datetime.format.DateTimeComponents
import kotlinx.datetime.format.MonthNames
import kotlinx.datetime.format.char
import kotlinx.datetime.offsetIn
import kotlin.time.Instant

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

fun Instant.formatDate(): String = format(
    offset = offsetIn(TimeZone.currentSystemDefault()),
    format = DateTimeComponents.Format {
        day()
        char(' ')
        monthName(MonthNames.RUSSIAN_GENITIVE)
        char(' ')
        year()
    },
)
