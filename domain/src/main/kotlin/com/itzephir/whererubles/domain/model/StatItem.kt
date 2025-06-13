package com.itzephir.whererubles.domain.model

data class StatItem(
    val categoryId: CategoryId,
    val categoryName: String,
    val emoji: String,
    val amount: String,
)