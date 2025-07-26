package com.itzephir.whererubles.core.data.account.mapper

import com.itzephir.whererubles.core.data.account.model.AccountOperation
import com.itzephir.whererubles.core.storage.account.entity.AccountOperationEntity
import tech.mappie.api.ObjectMappie

object AccountOperationToAccountOperationBody :
    ObjectMappie<AccountOperation, AccountOperationEntity.AccountOperationBody>()
