package com.itzephir.whererubles.domain.model

/**
 * StatItem entity
 * naming issue for better backend compatibility
 */
data class StatItem(
    val categoryId: CategoryId,
    val categoryName: String,
    val emoji: String,
    val amount: String,
)
