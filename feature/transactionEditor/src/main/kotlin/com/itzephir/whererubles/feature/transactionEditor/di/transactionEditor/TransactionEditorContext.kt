package com.itzephir.whererubles.feature.transactionEditor.di.transactionEditor

import com.itzephir.whererubles.feature.transactionEditor.presentation.viewmodel.TransactionEditorViewModel
import javax.inject.Inject

class TransactionEditorContext @Inject constructor(
    val viewModelFactory: TransactionEditorViewModel.Factory,
)