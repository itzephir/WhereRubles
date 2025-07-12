package com.itzephir.whererubles.expenses.presentation.model

import android.os.Parcelable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import com.itzephir.whererubles.core.common.InstantParceler
import com.itzephir.whererubles.core.format.formatAmount
import com.itzephir.whererubles.core.format.formatTime
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.TypeParceler
import kotlin.time.Instant

/**
 * Expense entity
 */
@Parcelize
@TypeParceler<Instant, InstantParceler>()
@Immutable
data class Expense(
    val id: ExpenseId,
    val icon: String,
    val title: String,
    val amount: String,
    val currency: String,
    val time: Instant,
    val comment: String? = null,
    val account: Account,
    val category: Category,
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
