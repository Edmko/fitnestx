package ua.edmko.core_ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import ua.edmko.core_ui.MultipleEventsCutter
import ua.edmko.core_ui.R
import ua.edmko.core_ui.get
import ua.edmko.core_ui.theme.AppTheme

@Composable
fun AppButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    shape: Shape = AppTheme.shapes.full,
    elevation: Dp = 0.dp,
    contentPadding: PaddingValues = ButtonDefaults.ContentPadding,
    delayMillis: Long = 0L,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    content: @Composable RowScope.() -> Unit,
) {
    val multipleEventsCutter = if (delayMillis > 0) remember { MultipleEventsCutter.get() } else null
    Box(
        modifier = modifier
            .shadow(elevation, shape, true)
            .background(AppTheme.colorScheme.brandGradient)
            .clickable(
                interactionSource = interactionSource,
                indication = AppTheme.indication,
                enabled = enabled,
                onClick = { multipleEventsCutter?.processEvent { onClick() } ?: onClick() }
            )
            .semantics { role = Role.Button },
    ) {
        CompositionLocalProvider(
            LocalTextStyle provides AppTheme.typography.buttonText,
            LocalContentColor provides AppTheme.colorScheme.background
        ) {
            Row(
                Modifier
                    .defaultMinSize(
                        minWidth = ButtonDefaults.MinWidth,
                        minHeight = ButtonDefaults.MinHeight
                    )
                    .padding(contentPadding)
                    .align(Alignment.Center),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                content = content
            )
        }
    }
}

@Composable
fun TextButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    shape: Shape = AppTheme.shapes.full,
    elevation: Dp = 0.dp,
    delayMillis: Long = 0,
    contentPadding: PaddingValues = ButtonDefaults.ContentPadding,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
) {
    AppButton(
        onClick = onClick,
        enabled = enabled,
        shape = shape,
        elevation = elevation,
        delayMillis = delayMillis,
        contentPadding = contentPadding,
        interactionSource = interactionSource,
        modifier = modifier
    ) {
        Text(text = text, style = AppTheme.typography.buttonText, color = AppTheme.colorScheme.background)
    }
}

@Composable
fun TextButtonWithIcon(
    modifier: Modifier = Modifier,
    icon: Painter,
    text: String,
    onClick: () -> Unit,
    contentColor: Color = AppTheme.colorScheme.background,
    enabled: Boolean = true,
    shape: Shape = AppTheme.shapes.full,
    elevation: Dp = 0.dp,
    contentPadding: PaddingValues = ButtonDefaults.ContentPadding,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
) {
    AppButton(
        onClick = onClick,
        enabled = enabled,
        shape = shape,
        elevation = elevation,
        contentPadding = contentPadding,
        interactionSource = interactionSource,
        modifier = modifier
    ) {
        Icon(
            modifier = Modifier.size(24.dp),
            painter = icon,
            contentDescription = null,
            tint = contentColor
        )
        Text(
            modifier = Modifier.padding(start = 10.dp),
            text = text,
            color = contentColor
        )
    }
}

@Composable
fun IconButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    shape: Shape = CircleShape,
    brush: Brush = AppTheme.colorScheme.brandGradient,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    content: @Composable () -> Unit,
) {
    Box(
        modifier = modifier
            .size(50.dp)
            .clip(shape)
            .background(brush = brush, shape = shape)
            .clickable(
                onClick = onClick,
                enabled = enabled,
                role = Role.Button,
                interactionSource = interactionSource,
                indication = ripple()
            ),
        contentAlignment = Alignment.Center
    ) {
        content()
    }
}

@Preview
@Composable
fun AppButtonPreview() {
    AppTheme {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .background(Color.White)
        ) {
            TextButton(
                text = "Button",
                onClick = {},
                modifier = Modifier
                    .padding(30.dp)
                    .fillMaxWidth()
                    .height(60.dp)
                    .align(Alignment.Center)
            )
        }
    }
}

@Preview
@Composable
fun TextButtonWithIconPreview() {
    AppTheme {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .background(Color.White)
        ) {
            TextButtonWithIcon(
                icon = painterResource(id = R.drawable.ic_login),
                text = "Button",
                onClick = {},
                modifier = Modifier
                    .padding(30.dp)
                    .fillMaxWidth()
                    .height(60.dp)
                    .align(Alignment.Center)
            )
        }
    }
}