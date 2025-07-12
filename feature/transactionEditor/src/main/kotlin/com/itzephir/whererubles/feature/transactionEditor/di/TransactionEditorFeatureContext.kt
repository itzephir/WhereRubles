package com.itzephir.whererubles.feature.transactionEditor.di

import com.itzephir.whererubles.feature.transactionEditor.di.transactionEditor.TransactionEditorDependencies
import com.itzephir.whererubles.feature.transactionEditor.domain.usecase.CreateOrUpdateTransactionUseCase
import com.itzephir.whererubles.feature.transactionEditor.presentation.model.Transaction
import com.itzephir.whererubles.feature.transactionEditor.presentation.model.TransactionId
import javax.inject.Inject

class TransactionEditorFeatureContext
@Inject constructor(
    override val createOrUpdateTransactionUseCase: CreateOrUpdateTransactionUseCase,
    override val transactionId: TransactionId?,
    override val transaction: Transaction,
) : TransactionEditorDependencies