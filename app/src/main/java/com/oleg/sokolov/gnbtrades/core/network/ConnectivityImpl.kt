package com.oleg.sokolov.gnbtrades.core.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class ConnectivityImpl @Inject constructor(
    @ApplicationContext val context: Context
) : Connectivity {

  override fun hasNetworkAccess(): Boolean {
    val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val capability = connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
    return capability?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) ?: false
  }
}