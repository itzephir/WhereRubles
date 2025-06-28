package com.itzephir.whererubles.domain.model

/**
 * Category Entity
 */
data class Category(
    val id: CategoryId,
    val name: String,
    val emoji: String,
    val isIncome: Boolean,
)
