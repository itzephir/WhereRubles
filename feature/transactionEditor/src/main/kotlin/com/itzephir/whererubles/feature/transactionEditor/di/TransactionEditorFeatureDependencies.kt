package com.itzephir.whererubles.feature.transactionEditor.di

import com.itzephir.whererubles.core.data.transaction.TransactionInteractor
import com.itzephir.whererubles.core.di.NetworkDependencies

interface TransactionEditorFeatureDependencies : NetworkDependencies {
    val transactionInteractor: TransactionInteractor
}
