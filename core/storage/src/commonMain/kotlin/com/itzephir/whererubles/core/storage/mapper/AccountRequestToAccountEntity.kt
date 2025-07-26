package com.itzephir.whererubles.core.storage.mapper

import com.itzephir.whererubles.core.storage.account.entity.AccountEntity
import com.itzephir.whererubles.core.storage.account.model.AccountRequest
import tech.mappie.api.ObjectMappie
import kotlin.time.Clock

object AccountRequestToAccountEntity: ObjectMappie<AccountRequest, AccountEntity>() {
    override fun map(from: AccountRequest): AccountEntity = mapping {
        to::createdAt fromValue Clock.System.now()
        to::updatedAt fromValue Clock.System.now()
    }
}