package com.itzephir.whererubles.feature.account.data.repository

import com.itzephir.whererubles.core.network.account.AccountError.ReadAllError
import com.itzephir.whererubles.feature.account.domain.error.AccountError.GetAccountError

fun ReadAllError.map() = when (this) {
    is ReadAllError.Unauthorized -> GetAccountError.Unauthorized

    is ReadAllError.Else         -> GetAccountError.Else(cause)
}
