package com.itzephir.whererubles.core.connection

import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ConnectionMonitor @Inject constructor(
    private val connectivityManager: ConnectivityManager,
    @ConnectionScope coroutineScope: CoroutineScope,
) {
    val status = callbackFlow {
        val callback = object : ConnectivityManager.NetworkCallback() {
            override fun onCapabilitiesChanged(
                network: Network,
                networkCapabilities: NetworkCapabilities,
            ) {
                super.onCapabilitiesChanged(network, networkCapabilities)
                val connected = networkCapabilities.hasCapabilities(
                    NetworkCapabilities.NET_CAPABILITY_INTERNET,
                    NetworkCapabilities.NET_CAPABILITY_VALIDATED,
                )
                trySend(if (connected) ConnectionStatus.CONNECTED else ConnectionStatus.NOT_CONNECTED)
            }

            override fun onAvailable(network: Network) {
                super.onAvailable(network)
                trySend(ConnectionStatus.CONNECTED)
            }

            override fun onLost(network: Network) {
                super.onLost(network)
                trySend(ConnectionStatus.NOT_CONNECTED)
            }

            override fun onUnavailable() {
                super.onUnavailable()
                trySend(ConnectionStatus.NOT_CONNECTED)
            }
        }

        connectivityManager.registerDefaultNetworkCallback(callback)

        awaitClose {
            connectivityManager.unregisterNetworkCallback(callback)
        }
    }.stateIn(
        scope = coroutineScope,
        started = SharingStarted.Lazily,
        initialValue = getCurrentConnectionStatus()
    )

    val currentStatus: ConnectionStatus
        get() {
            Log.d("CurrentNetworkStatus", status.value.toString())
            return status.value
        }

    fun getCurrentConnectionStatus(): ConnectionStatus {
        val capabilities = getNetworkCapabilities() ?: return ConnectionStatus.NOT_CONNECTED
        val isConnected = capabilities.hasCapabilities(
            NetworkCapabilities.NET_CAPABILITY_INTERNET,
            NetworkCapabilities.NET_CAPABILITY_VALIDATED,
        )
        return if (isConnected) ConnectionStatus.CONNECTED else ConnectionStatus.NOT_CONNECTED
    }

    private fun getNetworkCapabilities(): NetworkCapabilities? {
        val network = connectivityManager.activeNetwork
            ?: return null
        return connectivityManager.getNetworkCapabilities(network)
    }


    companion object {
        internal fun NetworkCapabilities.hasCapabilities(vararg capabilities: Int): Boolean =
            capabilities.fold(true) { acc, capability -> acc && hasCapability(capability) }
    }
}
