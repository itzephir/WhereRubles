package com.itzephir.whererubles.core.data.account.mapper

import com.itzephir.whererubles.core.network.account.UpdateAccountRequest
import com.itzephir.whererubles.core.storage.account.entity.AccountOperationEntity
import tech.mappie.api.ObjectMappie

object AccountOperationBodyToUpdateAccountRequest :
    ObjectMappie<AccountOperationEntity.AccountOperationBody, UpdateAccountRequest>()
