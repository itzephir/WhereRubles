package com.itzephir.whererubles.core.network.account

import com.itzephir.whererubles.core.model.Id

const val ACCOUNTS = "accounts"

fun accountsById(id: Id) = "$ACCOUNTS/${id.value}"