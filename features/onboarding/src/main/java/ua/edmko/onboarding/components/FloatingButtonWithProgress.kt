package ua.edmko.onboarding.components

import androidx.annotation.FloatRange
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import ua.edmko.core_ui.components.IconButton
import ua.edmko.core_ui.theme.AppTheme

@Composable
fun FloatingButtonWithProgress(
    modifier: Modifier = Modifier,
    @FloatRange(0.0, 1.0) progress: Float,
    painter: Painter,
    onClick: () -> Unit,
) {
    Box(modifier = modifier) {
        CircularProgressIndicator(
            color = AppTheme.colorScheme.brandLight,
            strokeWidth = 2.dp,
            modifier = Modifier.fillMaxSize(),
            gapSize = 0.dp,
            trackColor = AppTheme.colorScheme.border,
            progress = { progress }
        )
        IconButton(
            modifier = Modifier
                .padding(6.dp)
                .align(Alignment.Center)
                .background(AppTheme.colorScheme.brandGradient, shape = CircleShape),
            onClick = onClick,
        ) {
            Icon(painter = painter, contentDescription = null, tint = AppTheme.colorScheme.background)
        }
    }
}