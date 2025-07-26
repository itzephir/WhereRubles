package com.itzephir.whererubles.core.data.category.model

import com.itzephir.whererubles.core.model.Id

data class Category(
    val id: Id,
    val name: String,
    val emoji: String,
    val type: Type,
) {
    enum class Type {
        EXPENSE, INCOME
    }
}