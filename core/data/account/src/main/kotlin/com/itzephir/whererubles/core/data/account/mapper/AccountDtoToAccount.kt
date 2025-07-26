package com.itzephir.whererubles.core.data.account.mapper

import com.itzephir.whererubles.core.data.account.model.Account
import com.itzephir.whererubles.core.network.account.AccountDto
import tech.mappie.api.ObjectMappie

object AccountDtoToAccount: ObjectMappie<AccountDto, Account>()
