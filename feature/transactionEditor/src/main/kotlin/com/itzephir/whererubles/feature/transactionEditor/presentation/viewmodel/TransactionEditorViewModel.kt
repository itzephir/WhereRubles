package com.itzephir.whererubles.feature.transactionEditor.presentation.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import com.itzephir.whererubles.feature.transactionEditor.domain.usecase.CreateOrUpdateTransactionUseCase
import com.itzephir.whererubles.feature.transactionEditor.presentation.action.TransactionEditorAction
import com.itzephir.whererubles.feature.transactionEditor.presentation.context.TransactionEditorContext
import com.itzephir.whererubles.feature.transactionEditor.presentation.intent.TransactionEditorIntent
import com.itzephir.whererubles.feature.transactionEditor.presentation.mapper.map
import com.itzephir.whererubles.feature.transactionEditor.presentation.model.Transaction
import com.itzephir.whererubles.feature.transactionEditor.presentation.model.TransactionId
import com.itzephir.whererubles.feature.transactionEditor.presentation.state.TransactionEditorState
import com.itzephir.whererubles.feature.transactionEditor.presentation.store.TransactionEditorStore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.datetime.LocalTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.atTime
import kotlinx.datetime.toInstant
import kotlinx.datetime.toLocalDateTime
import pro.respawn.flowmvi.android.StoreViewModel
import pro.respawn.flowmvi.dsl.intent
import pro.respawn.flowmvi.dsl.state
import javax.inject.Inject
import kotlin.reflect.KClass
import kotlin.time.Clock
import kotlin.time.Instant

class TransactionEditorViewModel(
    savedStateHandle: SavedStateHandle,
    private val initialTransactionId: TransactionId?,
    private val initialTransaction: Transaction,
    private val createOrUpdateTransaction: CreateOrUpdateTransactionUseCase,
) : StoreViewModel<TransactionEditorState, TransactionEditorIntent, TransactionEditorAction>(
    TransactionEditorStore(
        savedStateHandle,
        initial = TransactionEditorState.Edit(initialTransactionId, initialTransaction),
    ) {
        updateState { TransactionEditorState.Edit(initialTransactionId, initialTransaction) }
    }
) {
    fun init(transactionId: TransactionId?, transaction: Transaction) = intent {
        println("init $transactionId: $transaction")
        updateState {
            println("$transactionId")
            TransactionEditorState.Edit(transactionId, transaction)
        }
        println(state)
    }

    fun changeAmount(amount: String) = (state as? TransactionEditorState.Edit)?.let { state ->
        intent {
            updateState {
                state.copy(transaction = state.transaction.copy(amount = amount))
            }
        }
    }

    fun changeComment(comment: String) = (state as? TransactionEditorState.Edit)?.let { state ->
        intent {
            updateState {
                state.copy(transaction = state.transaction.copy(comment = comment))
            }
        }
    }

    fun changeDate(date: Long?) = (state as? TransactionEditorState.Edit)?.let { state ->
        val date = date ?: return@let
        intent {
            updateState {
                val time =
                    state.transaction.transactionDate?.toLocalDateTime(TimeZone.currentSystemDefault())?.time
                        ?: LocalTime.fromSecondOfDay(0)

                val dateInstant = Instant.fromEpochMilliseconds(date)
                val localDate = dateInstant.toLocalDateTime(TimeZone.currentSystemDefault()).date
                state.copy(
                    transaction = state.transaction.copy(
                        transactionDate = localDate.atTime(time)
                            .toInstant(TimeZone.currentSystemDefault()),
                    )
                )
            }
        }
    }

    fun changeTime(time: Long?) = (state as? TransactionEditorState.Edit)?.let { state ->
        val time = time ?: return@let
        intent {
            updateState {
                val date =
                    (state.transaction.transactionDate?.toLocalDateTime(TimeZone.currentSystemDefault())
                        ?: Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())).date
                state.copy(
                    transaction = state.transaction.copy(
                        transactionDate = date.atTime(LocalTime.fromSecondOfDay(time.toInt()))
                            .toInstant(TimeZone.currentSystemDefault())
                    )
                )
            }
        }
    }

    fun confirm() = (state as? TransactionEditorState.Edit)?.let { state ->
        intent {
            updateState {
                TransactionEditorState.Loading(
                    transactionId = state.transactionId,
                    transaction = state.transaction,
                )
            }
            assert(state.canConfirm)
            confirmRequest()
        }
    }

    fun retry() = intent {
        val error = state as? TransactionEditorState.Error ?: return@intent

        confirmRequest()
    }

    private suspend fun TransactionEditorContext.confirmRequest() {
        withContext(Dispatchers.IO) {
            createOrUpdateTransaction(
                id = state.transactionId?.map(),
                transaction = state.transaction.map(),
            ).fold(
                ifLeft = {
                    updateState {
                        TransactionEditorState.Error(transactionId, transaction)
                    }
                },
                ifRight = {
                    println(it)
                    action(TransactionEditorAction.Confirmed)
                },
            )
        }
    }

    class Factory @Inject constructor(
        private val transactionId: TransactionId?,
        private val transaction: Transaction,
        private val createOrUpdateTransactionUseCase: CreateOrUpdateTransactionUseCase,
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: KClass<T>, extras: CreationExtras): T {
            val savedStateHandle = extras.createSavedStateHandle()

            @Suppress("UNCHECKED_CAST")
            return TransactionEditorViewModel(
                savedStateHandle = savedStateHandle,
                initialTransactionId = transactionId,
                initialTransaction = transaction,
                createOrUpdateTransaction = createOrUpdateTransactionUseCase,
            ) as T
        }
    }
}

