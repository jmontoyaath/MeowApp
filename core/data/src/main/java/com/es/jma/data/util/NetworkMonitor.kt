package com.es.jma.data.util

import android.Manifest
import androidx.annotation.RequiresPermission
import kotlinx.coroutines.flow.Flow

interface NetworkMonitor {
    @get:RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
    val isOnline: Flow<Boolean>
}