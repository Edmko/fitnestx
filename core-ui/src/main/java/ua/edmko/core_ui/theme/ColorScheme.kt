package ua.edmko.core_ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

@Immutable
data class ColorScheme(
    val brandLight: Color,
    val brandDark: Color,
    val secondaryLight: Color,
    val secondaryDark: Color,
    val background: Color,
    val border: Color,
    val baseTextColor: Color,
    val minorGrayDark: Color,
    val minorGrayMedium: Color,
    val minorGrayLight: Color,
) {

    val brandGradient = Brush.linearGradient(colors = listOf(brandLight, brandDark))
    val secondaryGradient = Brush.linearGradient(colors = listOf(secondaryDark, secondaryLight))
}