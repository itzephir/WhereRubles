package com.itzephir.whererubles.feature.income.presentation.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Income(
    val id: IncomeId,
    val title: String,
    val amount: String,
) : Parcelable
