package com.itzephir.whererubles.feature.transactionEditor.ui.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import com.itzephir.whererubles.feature.transactionEditor.di.DaggerTransactionEditorFeatureComponent
import com.itzephir.whererubles.feature.transactionEditor.di.TransactionEditorFeatureDependencies
import com.itzephir.whererubles.feature.transactionEditor.di.transactionEditor.DaggerTransactionEditorComponent
import com.itzephir.whererubles.feature.transactionEditor.presentation.model.Transaction
import com.itzephir.whererubles.feature.transactionEditor.presentation.model.TransactionId
import com.itzephir.whererubles.feature.transactionEditor.presentation.viewmodel.TransactionEditorViewModel
import com.itzephir.whererubles.feature.transactionEditor.ui.component.TransactionEditorScreenComponent

@Composable
fun TransactionEditorScreen(
    transactionId: TransactionId? = null,
    transaction: Transaction,
    currency: String,
    transactionEditorFeatureDependencies: TransactionEditorFeatureDependencies,
    onConfirm: () -> Unit,
) {
    val transactionEditorFeatureComponent = remember(
        transactionId,
        transaction,
        currency,
        transactionEditorFeatureDependencies,
    ) {
        println(transactionId)
        DaggerTransactionEditorFeatureComponent.factory().create(
            transactionId,
            transaction,
            currency,
            transactionEditorFeatureDependencies,
        )
    }

    val transactionEditorFeatureContext =
        transactionEditorFeatureComponent.transactionEditorFeatureContext

    val transactionEditorComponent = remember {
        println(transactionId)
        DaggerTransactionEditorComponent.factory().create(transactionEditorFeatureContext)
    }

    val transactionEditorContext = transactionEditorComponent.transactionEditorContext

    val viewModel =
        viewModel<TransactionEditorViewModel>(factory = transactionEditorContext.viewModelFactory)

    LaunchedEffect(Unit) {
        viewModel.init(transactionId, transaction)
    }

    TransactionEditorScreenComponent(viewModel = viewModel, onConfirm = onConfirm)
}