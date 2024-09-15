package ua.edmko.core_ui.theme

import androidx.compose.material3.CheckboxColors
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.runtime.Composable
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
    val textColor: Color,
    val minorGrayDark: Color,
    val minorGrayMedium: Color,
    val minorGrayLight: Color,
) {

    val brandGradient = Brush.linearGradient(colors = listOf(brandLight, brandDark))
    val secondaryGradient = Brush.linearGradient(colors = listOf(secondaryDark, secondaryLight))
    val horizontalBrandGradient = Brush.horizontalGradient(colors = listOf(brandLight, brandDark))
    val horizontalSecondaryGradient = Brush.horizontalGradient(colors = listOf(secondaryDark, secondaryLight))

    internal var cachedCheckBoxColors: CheckboxColors? = null
}


val ColorScheme.checkboxColors: CheckboxColors
    @Composable
    get() {
        return cachedCheckBoxColors ?: CheckboxDefaults.colors().copy(
            uncheckedBorderColor = AppTheme.colorScheme.minorGrayMedium,
            checkedBoxColor = AppTheme.colorScheme.brandDark,
            checkedBorderColor = AppTheme.colorScheme.brandDark
        ).also { cachedCheckBoxColors = it }
    }