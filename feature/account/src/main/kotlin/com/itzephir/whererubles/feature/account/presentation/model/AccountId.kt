package com.itzephir.whererubles.feature.account.presentation.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * Id of the account
 */
@JvmInline
@Parcelize
value class AccountId(val value: Int): Parcelable
