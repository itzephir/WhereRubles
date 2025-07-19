package com.itzephir.whererubles.core.connection

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.util.Log
import androidx.core.content.getSystemService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

class ConnectionMonitor @Inject constructor(
    context: Context,
) {
    private val connectivityManager =
        requireNotNull(context.getSystemService<ConnectivityManager>())

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
                trySend(if (connected) ConnectionStatus.CONNECTED else ConnectionStatus.NO_CONNECTION)
            }

            override fun onAvailable(network: Network) {
                super.onAvailable(network)
                trySend(ConnectionStatus.CONNECTED)
            }

            override fun onLost(network: Network) {
                super.onLost(network)
                trySend(ConnectionStatus.NO_CONNECTION)
            }

            override fun onUnavailable() {
                super.onUnavailable()
                trySend(ConnectionStatus.NO_CONNECTION)
            }
        }

        connectivityManager.registerDefaultNetworkCallback(callback)

        awaitClose {
            connectivityManager.unregisterNetworkCallback(callback)
        }
    }.stateIn(
        scope = CoroutineScope(SupervisorJob() + Dispatchers.Default),
        started = SharingStarted.Lazily,
        initialValue = getCurrentConnectionStatus()
    )

    fun getCurrentConnectionStatus(): ConnectionStatus {
        val network = connectivityManager.activeNetwork
            ?: return ConnectionStatus.NO_CONNECTION
        val capabilities = connectivityManager.getNetworkCapabilities(network)
            ?: return ConnectionStatus.NO_CONNECTION
        val isConnected = capabilities.hasCapabilities(
            NetworkCapabilities.NET_CAPABILITY_INTERNET,
            NetworkCapabilities.NET_CAPABILITY_VALIDATED,
        )
        return if (isConnected) ConnectionStatus.CONNECTED else ConnectionStatus.NO_CONNECTION
    }

    fun currentStatus(): ConnectionStatus {
        Log.d("CurrentNetworkStatus", status.value.toString())
        return status.value
    }

    companion object {
        private fun NetworkCapabilities.hasCapabilities(vararg capabilities: Int): Boolean =
            capabilities.fold(true) { acc, capability -> acc && hasCapability(capability) }
    }
}