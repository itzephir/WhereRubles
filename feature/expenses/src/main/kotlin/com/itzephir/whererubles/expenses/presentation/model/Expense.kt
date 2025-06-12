package com.itzephir.whererubles.expenses.presentation.model

import android.os.Parcelable
import androidx.compose.runtime.Immutable
import kotlinx.parcelize.Parcelize

@Parcelize
@Immutable
data class Expense(
    val id: ExpenseId,
    val icon: String,
    val title: String,
    val price: String,
    val comment: String? = null,
) : Parcelable