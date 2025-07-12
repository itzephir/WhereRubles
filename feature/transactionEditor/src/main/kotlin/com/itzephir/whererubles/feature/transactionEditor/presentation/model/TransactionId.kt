package com.itzephir.whererubles.feature.transactionEditor.presentation.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class TransactionId(val value: Int) : Parcelable