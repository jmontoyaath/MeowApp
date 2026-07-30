package com.es.jma.designsystem.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp

@Composable
fun CountryFlag(
    code: String,
    modifier: Modifier = Modifier,
    size: TextUnit = 32.sp
) {
    Text(
        text = countryCodeToEmoji(code),
        fontSize = size,
        modifier = modifier
    )
}

fun countryCodeToEmoji(countryCode: String): String {
    if (countryCode.length != 2) return "🌐"
    val upper = countryCode.uppercase()
    val first = Character.codePointAt(upper, 0) - 0x41 + 0x1F1E6
    val second = Character.codePointAt(upper, 1) - 0x41 + 0x1F1E6
    return String(Character.toChars(first)) + String(Character.toChars(second))
}