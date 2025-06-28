package com.itzephir.whererubles.feature.income.presentation.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * Id of income
 */
@JvmInline
@Parcelize
value class IncomeId(val value: Int) : Parcelable
