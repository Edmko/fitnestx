package ua.edmko.core_ui.components

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
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
import ua.edmko.core_ui.models.InputModel
import ua.edmko.core_ui.theme.AppTheme

@Composable
fun EditText(
    modifier: Modifier = Modifier,
    value: String,
    singleLine: Boolean = false,
    maxLines: Int = if (singleLine) 1 else Int.MAX_VALUE,
    minLines: Int = 1,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    onValueChanged: (String) -> Unit = {},
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    label: @Composable (() -> Unit)? = null,
    supportingText: @Composable (() -> Unit)? = null,
    isError: Boolean = false,
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
                cursorColor = AppTheme.colorScheme.brandLight,
                errorTextColor = AppTheme.colorScheme.error,
            ),
            shape = AppTheme.shapes.large,
            maxLines = maxLines,
            minLines = minLines,
            singleLine = singleLine,
            trailingIcon = trailingIcon,
            leadingIcon = leadingIcon,
            label = label,
            interactionSource = interactionSource,
            supportingText = supportingText,
            isError = isError,
            value = value,
            onValueChange = onValueChanged,
            keyboardActions = keyboardActions,
            keyboardOptions = keyboardOptions
        )
    }
}

@Composable
fun EditText(
    modifier: Modifier = Modifier,
    labelText: String,
    model: InputModel,
    onValueChanged: (String) -> Unit,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    leadingIconPainter: Painter? = null,
) {
    EditText(
        keyboardActions = keyboardActions,
        keyboardOptions = keyboardOptions,
        value = model.value,
        modifier = modifier,
        singleLine = true,
        onValueChanged = { if (it.length <= model.maxLength) onValueChanged(it) },
        leadingIcon = if (leadingIconPainter == null) null else getIcon(leadingIconPainter),
        label = {
            Text(
                modifier = Modifier,
                text = labelText,
                style = AppTheme.typography.smallRegular.copy(AppTheme.colorScheme.minorGrayMedium)
            )
        },
        supportingText = model.errorMessage?.let { getSupportingText(model.errorMessage.unwrap()) },
    )
}

private fun getSupportingText(text: String) = @Composable {
    Text(
        modifier = Modifier,
        text = text,
        style = AppTheme.typography.smallRegular.copy(AppTheme.colorScheme.error),
    )
}

private fun getIcon(painter: Painter) = @Composable { Icon(painter, contentDescription = null, tint = AppTheme.colorScheme.minorGrayDark) }