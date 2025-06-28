package com.itzephir.whererubles.domain.model

import kotlinx.datetime.Instant

/**
 * DateTime period unit entity
 */
data class DateTimePeriod(
    val startDate: Instant,
    val endDate: Instant,
)
