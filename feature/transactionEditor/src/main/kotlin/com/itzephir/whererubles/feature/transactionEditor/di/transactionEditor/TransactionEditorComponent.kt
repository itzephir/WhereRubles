package com.itzephir.whererubles.feature.transactionEditor.di.transactionEditor

import dagger.Component

@Component(
    dependencies = [TransactionEditorDependencies::class],
    modules = [TransactionEditorModule::class]
)
interface TransactionEditorComponent {
    @Component.Factory
    interface Factory {
        fun create(
            transactionEditorDependencies: TransactionEditorDependencies,
        ): TransactionEditorComponent
    }

    val transactionEditorContext: TransactionEditorContext
}