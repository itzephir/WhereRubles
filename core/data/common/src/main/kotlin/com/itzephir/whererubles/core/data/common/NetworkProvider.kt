package com.itzephir.whererubles.core.data.common

fun interface NetworkProvider {
    fun isConnected(): Boolean
}

inline fun <T> NetworkProvider.fold(ifConnected: () -> T, ifNotConnected: () -> T): T =
    if (isConnected()) ifConnected() else ifNotConnected()

inline fun <T> NetworkProvider.doAndFold(
    action: () -> T,
    ifConnected: (T) -> Unit,
    ifNotConnected: (T) -> Unit,
): T {
    return action().also { if (isConnected()) ifConnected(it) else ifNotConnected(it) }
}
