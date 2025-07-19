package com.itzephir.whererubles.core.storage.category

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.itzephir.whererubles.core.storage.common.Id

@Entity(tableName = "categories")
data class CategoryEntity(
    @PrimaryKey
    val id: Id,
    val name: String,
    val emoji: String,
    val isIncome: String,
)