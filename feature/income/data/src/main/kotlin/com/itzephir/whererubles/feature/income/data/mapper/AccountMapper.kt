package com.itzephir.whererubles.feature.income.data.mapper

import com.itzephir.whererubles.core.network.common.Id
import com.itzephir.whererubles.feature.income.domain.model.AccountId

fun Id.toAccountId() = AccountId(value)
