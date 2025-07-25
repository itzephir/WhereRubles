package com.itzephir.whererubles.core.data.transaction.mapper

import com.itzephir.whererubles.core.data.transaction.model.TransactionFull
import com.itzephir.whererubles.core.network.transaction.TransactionResponseDto
import tech.mappie.api.ObjectMappie

object TransactionResponseDtoToTransactionFull :
    ObjectMappie<TransactionResponseDto, TransactionFull>() {
    override fun map(from: TransactionResponseDto): TransactionFull = mapping {
        to::account fromProperty from::account via AccountBriefMapper
        to::category fromProperty from::category via CategoryMapper
    }

    private object AccountBriefMapper :
        ObjectMappie<TransactionResponseDto.AccountBrief, TransactionFull.AccountBrief>()

    private object CategoryMapper :
        ObjectMappie<TransactionResponseDto.Category, TransactionFull.Category>() {
        override fun map(from: TransactionResponseDto.Category): TransactionFull.Category = mapping {
            to::type fromExpression { category ->
                if (category.isIncome) TransactionFull.Category.Type.INCOME else TransactionFull.Category.Type.EXPENSE
            }
        }
    }
}