package com.es.jma.ui

import android.content.Context
import androidx.annotation.ColorInt
import androidx.browser.customtabs.CustomTabColorSchemeParams
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.net.toUri

fun Context.openCustomTab(url: String, @ColorInt toolbarColor: Int = 1) {
    val colorScheme = CustomTabColorSchemeParams.Builder()
        .setToolbarColor(toolbarColor)
        .build()

    CustomTabsIntent.Builder()
        .setDefaultColorSchemeParams(colorScheme)
        .setShowTitle(true)
        .build()
        .launchUrl(this, url.toUri())
}