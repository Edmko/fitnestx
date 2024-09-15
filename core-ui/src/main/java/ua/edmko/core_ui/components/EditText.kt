package ua.edmko.core_ui.components

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import ua.edmko.core_ui.theme.AppTheme

@Composable
fun EditText(
    modifier: Modifier = Modifier,
    value: String,
    singleLine: Boolean = false,
    maxLines: Int = if (singleLine) 1 else Int.MAX_VALUE,
    minLines: Int = 1,
    onValueChanged: (String) -> Unit = {},
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    label: @Composable (() -> Unit)? = null,
    interactionSource: MutableInteractionSource? = null,
) {
    CompositionLocalProvider(
        LocalTextStyle provides AppTheme.typography.smallRegular
    ) {
        TextField(
            modifier = modifier,
            colors = TextFieldDefaults.colors().copy(
                unfocusedIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                focusedContainerColor = AppTheme.colorScheme.border,
                unfocusedContainerColor = AppTheme.colorScheme.border,
                cursorColor = AppTheme.colorScheme.brandLight
            ),
            shape = AppTheme.shapes.large,
            maxLines = maxLines,
            minLines = minLines,
            singleLine = singleLine,
            trailingIcon = trailingIcon,
            leadingIcon = leadingIcon,
            label = label,
            interactionSource = interactionSource,
            value = value,
            onValueChange = onValueChanged,
        )
    }
}

@Composable
fun EditText(
    modifier: Modifier = Modifier,
    labelText: String,
    value: String,
    onValueChanged: (String) -> Unit,
    leadingIconPainter: Painter? = null,
) {
    EditText(
        value = value,
        modifier = modifier,
        singleLine = true,
        onValueChanged = onValueChanged,
        leadingIcon = if (leadingIconPainter == null) null else getIcon(leadingIconPainter),
        label = {
            Text(
                modifier = Modifier,
                text = labelText,
                style = AppTheme.typography.smallRegular.copy(AppTheme.colorScheme.minorGrayMedium)
            )
        }
    )
}

private fun getIcon(painter: Painter) = @Composable { Icon(painter, contentDescription = null, tint = AppTheme.colorScheme.minorGrayDark) }