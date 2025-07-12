package com.itzephir.whererubles.feature.transactionEditor.di.transactionEditor

import com.itzephir.whererubles.feature.transactionEditor.domain.usecase.CreateOrUpdateTransactionUseCase
import com.itzephir.whererubles.feature.transactionEditor.presentation.model.Transaction
import com.itzephir.whererubles.feature.transactionEditor.presentation.model.TransactionId

interface TransactionEditorDependencies {
    val createOrUpdateTransactionUseCase: CreateOrUpdateTransactionUseCase
    val transactionId: TransactionId?
    val transaction: Transaction
}
