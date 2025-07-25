package com.itzephir.whererubles.feature.income.data.mapper

import com.itzephir.whererubles.core.model.Id
import com.itzephir.whererubles.feature.income.domain.model.AccountId

fun Id.toAccountId() = AccountId(value)
