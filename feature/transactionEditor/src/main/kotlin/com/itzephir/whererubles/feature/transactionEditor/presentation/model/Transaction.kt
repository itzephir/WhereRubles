package com.itzephir.whererubles.feature.transactionEditor.presentation.model

import android.os.Parcelable
import androidx.compose.runtime.Stable
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import kotlin.time.Instant

@Parcelize
@Stable
@Serializable
data class Transaction(
    val account: Account? = null,
    val category: Category? = null,
    val amount: String = "",
    @Contextual val transactionDate: Instant? = null,
    val comment: String?,
) : Parcelable {
    @IgnoredOnParcel
    val accountId: AccountId? = account?.id

    @IgnoredOnParcel
    val categoryId: CategoryId? = category?.id

    @IgnoredOnParcel
    val accountName = account?.name

    @IgnoredOnParcel
    val categoryName = category?.name

    @Parcelize
    @Serializable
    data class Account(
        val id: AccountId,
        val name: String,
    ) : Parcelable

    @Parcelize
    @Serializable
    data class Category(
        val id: CategoryId,
        val name: String,
    ) : Parcelable

    @Parcelize
    @JvmInline
    @Serializable
    value class AccountId(val value: Int) : Parcelable

    @Parcelize
    @JvmInline
    @Serializable
    value class CategoryId(val value: Int) : Parcelable
}