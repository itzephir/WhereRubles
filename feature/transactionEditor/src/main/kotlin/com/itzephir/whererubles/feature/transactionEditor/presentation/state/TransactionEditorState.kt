package com.itzephir.whererubles.feature.transactionEditor.presentation.state

import android.os.Parcelable
import androidx.compose.runtime.Stable
import com.itzephir.whererubles.feature.transactionEditor.presentation.model.Transaction
import com.itzephir.whererubles.feature.transactionEditor.presentation.model.TransactionId
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize
import pro.respawn.flowmvi.api.MVIState

sealed interface TransactionEditorState : MVIState, Parcelable {
    val transaction: Transaction
    val transactionId: TransactionId?

    @Parcelize
    data class Edit(
        override val transactionId: TransactionId? = null,
        override val transaction: Transaction,
    ) : TransactionEditorState {
        @IgnoredOnParcel
        @Stable
        val canConfirm: Boolean = canConfirm()

        @Stable
        fun canConfirm(): Boolean {
            return (transaction.category != null) &&
                    (transaction.account != null) &&
                    (transaction.transactionDate != null) &&
                    transaction.amount.isNotBlank()
        }
    }

    @Parcelize
    data class Loading(
        override val transactionId: TransactionId? = null,
        override val transaction: Transaction,
    ) : TransactionEditorState

    @Parcelize
    data class Error(
        override val transactionId: TransactionId? = null,
        override val transaction: Transaction,
    ) : TransactionEditorState
}

