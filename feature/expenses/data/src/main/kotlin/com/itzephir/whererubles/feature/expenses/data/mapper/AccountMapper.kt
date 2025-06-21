package com.itzephir.whererubles.feature.expenses.data.mapper

import com.itzephir.whererubles.core.network.common.Id
import com.itzephir.whererubles.feature.expenses.domain.model.AccountId

fun Id.toAccountId() = AccountId(value)