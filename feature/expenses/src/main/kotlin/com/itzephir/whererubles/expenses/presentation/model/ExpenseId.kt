package com.itzephir.whererubles.expenses.presentation.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * Id of the expense
 */
@JvmInline
@Parcelize
value class ExpenseId(val value: Int) : Parcelable
