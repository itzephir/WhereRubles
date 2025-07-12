package com.itzephir.whererubles.feature.transactionEditor.di

import com.itzephir.whererubles.feature.transactionEditor.presentation.model.Transaction
import com.itzephir.whererubles.feature.transactionEditor.presentation.model.TransactionId
import dagger.Binds
import dagger.BindsInstance
import dagger.Component

@Component(
    dependencies = [TransactionEditorFeatureDependencies::class],
    modules = [TransactionEditorFeatureModule::class],
)
interface TransactionEditorFeatureComponent {
    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance transactionId: TransactionId? = null,
            @BindsInstance transaction: Transaction,
            @BindsInstance currency: String,
            transactionEditorFeatureDependencies: TransactionEditorFeatureDependencies,
        ): TransactionEditorFeatureComponent
    }

    val transactionEditorFeatureContext: TransactionEditorFeatureContext
}