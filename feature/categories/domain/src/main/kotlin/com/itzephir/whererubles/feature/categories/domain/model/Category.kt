package com.itzephir.whererubles.feature.categories.domain.model

data class Category(
    val id: CategoryId,
    val name: String,
    val emoji: String,
    val isIncome: Boolean,
) {
    @JvmInline
    value class CategoryId(val value: Int)
}