package com.itzephir.whererubles.feature.transactionEditor.presentation.context

import com.itzephir.whererubles.feature.transactionEditor.presentation.action.TransactionEditorAction
import com.itzephir.whererubles.feature.transactionEditor.presentation.intent.TransactionEditorIntent
import com.itzephir.whererubles.feature.transactionEditor.presentation.state.TransactionEditorState
import pro.respawn.flowmvi.api.PipelineContext

typealias TransactionEditorContext =
        PipelineContext<TransactionEditorState, TransactionEditorIntent, TransactionEditorAction>