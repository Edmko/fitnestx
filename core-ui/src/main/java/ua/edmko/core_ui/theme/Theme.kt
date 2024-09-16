package ua.edmko.core_ui.theme

import androidx.compose.foundation.Indication
import androidx.compose.foundation.LocalIndication
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf

private val LightColorScheme = ColorScheme(
    brandLight = BlueLighter,
    brandDark = BlueDarker,
    secondaryLight = PurpleLighter,
    secondaryDark = PurpleDarker,
    background = White,
    border = WhiteAlmost,
    textColor = BlackAlmost,
    minorGrayLight = GrayLight,
    minorGrayMedium = GrayMedium,
    minorGrayDark = GrayDark,
    error = Red
)

@Composable
fun AppTheme(
    colorScheme: ColorScheme = LightColorScheme,
    shapes: Shapes = AppShapes,
    typography: Typography = AppTypography,
    dimensions: Dimensions = Dimensions(),
    content: @Composable () -> Unit,
) {
    CompositionLocalProvider(
        LocalAppColors provides colorScheme,
        LocalIndication provides ripple(),
        LocalShapes provides shapes,
        LocalTypography provides typography,
        LocalDimensions provides dimensions
    ) {
        content()
    }
}

/**
 * Contains functions to access the current theme values provided at the call site's position in
 * the hierarchy.
 */
object AppTheme {

    val colorScheme: ColorScheme
        @Composable
        @ReadOnlyComposable
        get() = LocalAppColors.current

    val typography: Typography
        @Composable
        @ReadOnlyComposable
        get() = LocalTypography.current

    val shapes: Shapes
        @Composable
        @ReadOnlyComposable
        get() = LocalShapes.current

    val indication: Indication
        @Composable
        @ReadOnlyComposable
        get() = LocalIndication.current

    val dimensions: Dimensions
        @Composable
        @ReadOnlyComposable
        get() = LocalDimensions.current
}

internal val LocalTypography = staticCompositionLocalOf { AppTypography }

internal val LocalShapes = staticCompositionLocalOf { AppShapes }

internal val LocalAppColors = staticCompositionLocalOf { LightColorScheme }

internal val LocalDimensions = staticCompositionLocalOf { Dimensions() }