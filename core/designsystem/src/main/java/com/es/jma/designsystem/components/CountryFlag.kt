package com.es.jma.designsystem.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.TextUnit
import com.es.jma.designsystem.theme.TextSize.Larger
import com.es.jma.utils.constants.ONE
import com.es.jma.utils.constants.ZERO

private const val ASCII_UPPER_A = 0x41
private const val REGIONAL_INDICATOR_A = 0x1F1E6
private const val FALLBACK_FLAG = "🌐"

@Composable
fun CountryFlag(
    code: String?,
    modifier: Modifier = Modifier,
    size: TextUnit = Larger
) {
    Text(
        text = countryCodeToEmoji(code),
        fontSize = size,
        modifier = modifier
    )
}

fun countryCodeToEmoji(countryCode: String?): String {
    if (countryCode?.length != 2) return FALLBACK_FLAG
    val upper = countryCode.uppercase()
    val first = Character.codePointAt(upper, ZERO) - ASCII_UPPER_A + REGIONAL_INDICATOR_A
    val second = Character.codePointAt(upper, ONE) - ASCII_UPPER_A + REGIONAL_INDICATOR_A
    return String(Character.toChars(first)) + String(Character.toChars(second))
}