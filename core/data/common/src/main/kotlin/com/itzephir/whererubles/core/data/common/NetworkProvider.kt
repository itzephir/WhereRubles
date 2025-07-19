package com.itzephir.whererubles.core.data.common

fun interface NetworkProvider {
    fun isConnected(): Boolean
}