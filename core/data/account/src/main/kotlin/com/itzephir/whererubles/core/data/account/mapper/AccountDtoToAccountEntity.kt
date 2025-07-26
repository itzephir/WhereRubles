package com.itzephir.whererubles.core.data.account.mapper

import com.itzephir.whererubles.core.network.account.AccountDto
import com.itzephir.whererubles.core.storage.account.entity.AccountEntity
import tech.mappie.api.ObjectMappie

object AccountDtoToAccountEntity: ObjectMappie<AccountDto, AccountEntity>()
