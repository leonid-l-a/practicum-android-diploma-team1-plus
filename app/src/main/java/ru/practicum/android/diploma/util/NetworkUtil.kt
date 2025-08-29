package ru.practicum.android.diploma.util

import android.Manifest
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.annotation.RequiresPermission

/**
 * Utility class for network-related operations.
 *
 * This class provides method to check internet availability.
 */
class NetworkUtil() {

    /**
     * Checks if internet connection is available.
     *
     * This function requires the ACCESS_NETWORK_STATE permission.
     *
     * It determines internet availability by checking for an active network and verifying
     * its capabilities, specifically if it has internet access and is validated.
     *
     * @param context The context used to access system services.
     * @return `true` if an internet connection is available and validated, `false` otherwise.
     */
    @RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
    fun isInternetAvailable(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        val network = connectivityManager.activeNetwork ?: return false
        val capabilities =
            connectivityManager.getNetworkCapabilities(network) ?: return false

        return capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) &&
            capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED)
    }
}
