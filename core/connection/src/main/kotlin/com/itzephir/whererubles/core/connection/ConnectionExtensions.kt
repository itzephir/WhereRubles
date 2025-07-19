package com.itzephir.whererubles.core.connection

suspend fun ConnectionMonitor.onConnected(block: suspend () -> Unit): ConnectionMonitor {
    if (status.value == ConnectionStatus.CONNECTED) block()
    return this
}

suspend fun ConnectionMonitor.onNotConnected(block: suspend () -> Unit): ConnectionMonitor {
    if (status.value == ConnectionStatus.NO_CONNECTION) block()
    return this
}