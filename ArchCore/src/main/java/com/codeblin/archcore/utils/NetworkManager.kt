package com.codeblin.archcore.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.launch

interface NetworkManager {
    fun isNetworkAvailable(): Boolean
    fun observeNetworkStatus(): Flow<NetworkStatus>
}

enum class NetworkStatus {
    AVAILABLE, UNAVAILABLE
}

class ConnectivityNetworkManager(context: Context) : NetworkManager {

    private val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    override fun isNetworkAvailable(): Boolean {
        val network = connectivityManager.activeNetwork ?: return false
        val capabilities = connectivityManager.getNetworkCapabilities(network) ?: return false
        return capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) ||
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)
    }

    override fun observeNetworkStatus(): Flow<NetworkStatus> = callbackFlow {
        var previousStatus: NetworkStatus? = null
        val networkCallback = object : ConnectivityManager.NetworkCallback() {
            override fun onAvailable(network: Network) {
                val newStatus = NetworkStatus.AVAILABLE
                if (newStatus != previousStatus) {
                    launch { send(newStatus) }
                    previousStatus = newStatus
                }
            }

            override fun onLost(network: Network) {
                val newStatus = NetworkStatus.UNAVAILABLE
                if (newStatus != previousStatus) {
                    launch { send(newStatus) }
                    previousStatus = newStatus
                }
            }
        }

        val networkRequest = NetworkRequest.Builder()
            .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
            .build()

        connectivityManager.registerNetworkCallback(networkRequest, networkCallback)

        awaitClose {
            connectivityManager.unregisterNetworkCallback(networkCallback)
        }
    }
}