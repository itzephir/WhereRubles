package com.itzephir.whererubles.core.data.account.mapper

import com.itzephir.whererubles.core.data.account.model.AccountFull
import com.itzephir.whererubles.core.storage.account.entity.AccountEntity
import tech.mappie.api.ObjectMappie

object AccountFullToAccountEntity: ObjectMappie<AccountFull, AccountEntity>()