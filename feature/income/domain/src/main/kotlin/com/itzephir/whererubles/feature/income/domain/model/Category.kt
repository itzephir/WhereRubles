package com.itzephir.whererubles.feature.income.domain.model

data class Category(
    val id: CategoryId,
    val name: String,
) {
    @JvmInline
    value class CategoryId(val value: Int)
}