package com.itzephir.whererubles.core.data.account.mapper

import com.itzephir.whererubles.core.network.account.CreateAccountRequest
import com.itzephir.whererubles.core.storage.account.entity.AccountOperationEntity
import tech.mappie.api.ObjectMappie

object AccountOperationBodyToCreateAccountRequest :
    ObjectMappie<AccountOperationEntity.AccountOperationBody, CreateAccountRequest>()
