package com.itzephir.whererubles.core.connection

suspend fun ConnectionMonitor.onConnected(block: suspend () -> Unit): ConnectionMonitor {
    if (currentStatus == ConnectionStatus.CONNECTED) block()
    return this
}

suspend fun ConnectionMonitor.onNotConnected(block: suspend () -> Unit): ConnectionMonitor {
    if (currentStatus == ConnectionStatus.NOT_CONNECTED) block()
    return this
}

suspend fun <T> ConnectionMonitor.fold(
    ifConnected: suspend () -> T,
    ifNotConnected: suspend () -> T,
): T = when (currentStatus) {
    ConnectionStatus.CONNECTED     -> ifConnected()
    ConnectionStatus.NOT_CONNECTED -> ifNotConnected()
}