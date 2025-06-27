package com.itzephir.whererubles.domain.model

import kotlinx.datetime.Instant

data class DateTimePeriod(
    val startDate: Instant,
    val endDate: Instant,
)
