package com.es.jma.ui

import android.content.Context
import androidx.annotation.ColorInt
import androidx.browser.customtabs.CustomTabColorSchemeParams
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.net.toUri
import com.es.jma.utils.constants.ONE

fun Context.openCustomTab(url: String, @ColorInt toolbarColor: Int = ONE) {
    val colorScheme = CustomTabColorSchemeParams.Builder()
        .setToolbarColor(toolbarColor)
        .build()

    CustomTabsIntent.Builder()
        .setDefaultColorSchemeParams(colorScheme)
        .setShowTitle(true)
        .build()
        .launchUrl(this, url.toUri())
}