package com.itzephir.whererubles.core.data.account.mapper

import com.itzephir.whererubles.core.data.account.model.Account
import com.itzephir.whererubles.core.storage.account.entity.AccountEntity
import tech.mappie.api.ObjectMappie

object AccountEntityToAccount: ObjectMappie<AccountEntity, Account>()
