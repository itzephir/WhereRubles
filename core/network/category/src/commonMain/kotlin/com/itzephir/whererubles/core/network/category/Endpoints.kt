package com.itzephir.whererubles.core.network.category

const val CATEGORIES = "categories"

fun categoriesByType(isIncome: Boolean) = "$CATEGORIES/type/$isIncome"