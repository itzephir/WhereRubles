package com.itzephir.whererubles.core.connection

import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.util.Log
import com.itzephir.whererubles.core.connection.ConnectionMonitor.Companion.hasCapabilities
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import io.mockk.mockkStatic
import kotlinx.coroutines.cancel
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.runTest
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class ConnectionMonitorTest {

    private lateinit var connectionMonitor: ConnectionMonitor

    @MockK
    private lateinit var mockConnectivityManager: ConnectivityManager

    private lateinit var mockNetwork: Network
    private lateinit var mockNetworkCapabilities: NetworkCapabilities

    private val testDispatcher = StandardTestDispatcher()
    private val testScope = TestScope(testDispatcher)

    @BeforeTest
    fun setup() {
        mockkStatic(Log::class)
        every { Log.d(any(), any()) } returns 0
        every { Log.d(any(), any<String>()) } returns 0

        mockConnectivityManager = mockk(relaxed = true)
        mockNetwork = mockk()
        mockNetworkCapabilities = mockk()

        connectionMonitor = ConnectionMonitor(
            connectivityManager = mockConnectivityManager,
            coroutineScope = testScope,
        )
    }

    @Test
    fun `initial status should be NOT_CONNECTED when no active network`() = runTest {
        every { mockConnectivityManager.activeNetwork } returns null

        val expected = ConnectionStatus.NOT_CONNECTED
        val actual = connectionMonitor.currentStatus

        assertEquals(expected, actual)
    }

    @Test
    fun `initial status should be CONNECTED when network has required capabilities`() =
        testScope.runTest {

            every {
                mockConnectivityManager.getNetworkCapabilities(mockConnectivityManager.activeNetwork)
                    ?.hasCapabilities(
                        NetworkCapabilities.NET_CAPABILITY_INTERNET,
                        NetworkCapabilities.NET_CAPABILITY_VALIDATED
                    )
            } returns true

            println(
                mockNetworkCapabilities.hasCapabilities(
                    NetworkCapabilities.NET_CAPABILITY_INTERNET,
                    NetworkCapabilities.NET_CAPABILITY_VALIDATED,
                )
            )

            val expected = ConnectionStatus.CONNECTED
            val actual = connectionMonitor.currentStatus

            assertEquals(expected, actual)
        }
}