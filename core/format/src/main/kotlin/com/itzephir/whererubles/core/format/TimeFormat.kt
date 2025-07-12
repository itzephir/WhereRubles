package com.itzephir.whererubles.core.format

import kotlinx.datetime.TimeZone
import kotlin.time.Instant
import kotlinx.datetime.format
import kotlinx.datetime.format.DateTimeComponents
import kotlinx.datetime.format.char
import kotlinx.datetime.offsetIn

fun Instant.formatTime() = format(
    offset = offsetIn(TimeZone.currentSystemDefault()),
    format = DateTimeComponents.Format() {
        hour()
        char(':')
        minute()
    },
)
