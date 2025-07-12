package com.itzephir.whererubles.feature.income.presentation.model

import android.icu.number.Precision.currency
import android.os.Parcelable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import com.itzephir.whererubles.core.common.InstantParceler
import com.itzephir.whererubles.core.format.formatAmount
import com.itzephir.whererubles.core.format.formatTime
import kotlin.time.Instant
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.TypeParceler

/**
 * Income entity
 */
@Parcelize
@TypeParceler<Instant, InstantParceler>()
@Immutable
data class Income(
    val id: IncomeId,
    val icon: String,
    val title: String,
    val amount: String,
    val time: Instant,
    val comment: String? = null,
    val account: Account,
    val category: Category,
    val currency: String,
) : Parcelable {
    @Parcelize
    data class Account(
        val id: AccountId,
        val name: String,
    ) : Parcelable {
        @Parcelize
        @JvmInline
        value class AccountId(val value: Int) : Parcelable
    }

    @Parcelize
    data class Category(
        val id: CategoryId,
        val name: String,
    ) : Parcelable {
        @Parcelize
        @JvmInline
        value class CategoryId(val value: Int) : Parcelable
    }

    @IgnoredOnParcel
    @Stable
    val timeString = time.formatTime()

    @IgnoredOnParcel
    @Stable
    val amountString = amount.formatAmount(currency)
}
