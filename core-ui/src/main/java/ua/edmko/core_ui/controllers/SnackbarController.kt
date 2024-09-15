package ua.edmko.core_ui.controllers

import androidx.annotation.StringRes
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.SnackbarVisuals
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.Stable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.res.stringResource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

/**
 * https://afigaliyev.medium.com/snackbar-state-management-best-practices-for-jetpack-compose-1a5963d86d98
 *
 * Provides a [SnackbarController] to its content.
 * @param snackbarHostState The [SnackbarHostState] to use.
 * @param coroutineScope The [CoroutineScope] to use.
 * @param content The content that will have access to the [SnackbarController].
 */
@Composable
fun ProvideSnackbarController(
    snackbarHostState: SnackbarHostState,
    coroutineScope: CoroutineScope,
    content: @Composable () -> Unit,
) {
    CompositionLocalProvider(
        LocalSnackbarController provides SnackbarController(
            snackbarHostState = snackbarHostState,
            coroutineScope = coroutineScope
        ),
        content = content
    )
}

/**
 * Static CompositionLocal that provides access to a [SnackbarController]. The value of the
 * [LocalSnackbarController] is set using the [CompositionLocalProvider] composable. If no
 * [SnackbarController] is provided, an error is thrown.
 */
val LocalSnackbarController = staticCompositionLocalOf<SnackbarController> {
    error("No SnackbarController provided.")
}

/**
 * Returns a [SnackbarController] that uses the given [snackbarHostState] and [coroutineScope] to display snackbars.
 *
 * @param snackbarHostState The [SnackbarHostState] to use.
 * @param coroutineScope The [CoroutineScope] to use.
 * @return A [SnackbarController] that uses the given [snackbarHostState] and [coroutineScope] to display snackbars.
 */
@Stable
fun SnackbarController(
    snackbarHostState: SnackbarHostState,
    coroutineScope: CoroutineScope,
): SnackbarController = SnackbarControllerImpl(
    snackbarHostState = snackbarHostState,
    coroutineScope = coroutineScope
)

/**
 * Implementation of the [SnackbarController] interface that uses a [SnackbarHostState] to show
 * Snackbar messages. The [coroutineScope] is used to launch coroutines for showing Snackbar messages.
 *
 * @param snackbarHostState The [SnackbarHostState] used to show Snackbar messages.
 * @param coroutineScope The [CoroutineScope] used to launch coroutines for showing Snackbar messages.
 */
@Immutable
private class SnackbarControllerImpl(
    private val snackbarHostState: SnackbarHostState,
    private val coroutineScope: CoroutineScope,
) : SnackbarController {
    /**
     * Shows a Snackbar message with the given parameters and invokes the [onSnackbarResult] callback
     * on the snackbar result.
     */
    override fun showMessage(
        message: String,
        actionLabel: String?,
        withDismissAction: Boolean,
        duration: SnackbarDuration,
        onSnackbarResult: (SnackbarResult) -> Unit,
    ) {
        coroutineScope.launch {
            snackbarHostState.showSnackbar(
                message = message,
                actionLabel = actionLabel,
                withDismissAction = withDismissAction,
                duration = duration
            ).let(onSnackbarResult)
        }
    }

    /**
     * Shows a Snackbar message with the given parameters and invokes the [onSnackbarResult] callback
     * on the snackbar result.
     */
    override fun showMessage(
        snackbarVisuals: SnackbarVisuals,
        onSnackbarResult: (SnackbarResult) -> Unit,
    ) {
        coroutineScope.launch {
            snackbarHostState.showSnackbar(visuals = snackbarVisuals).let(onSnackbarResult)
        }
    }
}

/**
 * An interface that provides methods to display snackbars.
 */
@Immutable
interface SnackbarController {
    fun showMessage(
        message: String,
        actionLabel: String? = null,
        withDismissAction: Boolean = false,
        duration: SnackbarDuration = SnackbarDuration.Short,
        onSnackbarResult: (SnackbarResult) -> Unit = {},
    )

    fun showMessage(
        snackbarVisuals: SnackbarVisuals,
        onSnackbarResult: (SnackbarResult) -> Unit = {},
    )
}

/**
 * Handles a [SnackbarMessage] by showing a Snackbar message or visuals using the [snackbarController].
 * The [snackbarMessage] can be either a [SnackbarMessage.Text] or a [SnackbarMessage.Visuals].
 * The [onDismissSnackbar] callback is invoked when the Snackbar is dismissed. If [snackbarMessage]
 * is null, this function returns early and does nothing.
 */

@Composable
fun SnackbarMessageHandler(
    snackbarMessage: SnackbarMessage?,
    onDismissSnackbar: () -> Unit,
    snackbarController: SnackbarController = LocalSnackbarController.current,
) {
    if (snackbarMessage == null) return

    when (snackbarMessage) {
        is SnackbarMessage.Text -> {
            val message = snackbarMessage.userMessage.asString()
            val actionLabel = snackbarMessage.actionLabelMessage?.asString()

            LaunchedEffect(snackbarMessage, onDismissSnackbar) {
                snackbarController.showMessage(
                    message = message,
                    actionLabel = actionLabel,
                    withDismissAction = snackbarMessage.withDismissAction,
                    duration = snackbarMessage.duration,
                    onSnackbarResult = snackbarMessage.onSnackbarResult
                )

                onDismissSnackbar()
            }
        }

        is SnackbarMessage.Visuals -> {
            LaunchedEffect(snackbarMessage, onDismissSnackbar) {
                snackbarController.showMessage(
                    snackbarVisuals = snackbarMessage.snackbarVisuals,
                    onSnackbarResult = snackbarMessage.onSnackbarResult
                )

                onDismissSnackbar()
            }
        }
    }
}

sealed interface SnackbarMessage {
    /**
     * Represents a text message to be displayed in a snackbar.
     *
     * @property userMessage text to be shown in the Snackbar.
     * @property actionLabelMessage optional action label to show as button in the Snackbar.
     * @property withDismissAction a boolean to show a dismiss action in the Snackbar. This is
     * recommended to be set to true better accessibility when a Snackbar is set with a
     * [SnackbarDuration.Indefinite].
     * @property duration duration of the Snackbar.
     * @property onSnackbarResult A callback for when the snackbar is dismissed or the action is performed.
     */
    data class Text(
        val userMessage: UserMessage,
        val actionLabelMessage: UserMessage? = null,
        val withDismissAction: Boolean = false,
        val duration: SnackbarDuration = SnackbarDuration.Short,
        val onSnackbarResult: (SnackbarResult) -> Unit = {},
    ) : SnackbarMessage

    /**
     * Represents a message with custom visuals to be displayed in a snackbar.
     *
     * @property snackbarVisuals Custom visuals for the snackbar.
     * @property onSnackbarResult A callback for when the snackbar is dismissed or the action is performed.
     */
    data class Visuals(
        val snackbarVisuals: SnackbarVisuals,
        val onSnackbarResult: (SnackbarResult) -> Unit = {},
    ) : SnackbarMessage

    companion object {
        /**
         * Returns a new [SnackbarMessage.Text] instance.
         *
         * @param userMessage text to be shown in the Snackbar.
         * @param actionLabelMessage optional action label to show as button in the Snackbar.
         * @param withDismissAction a boolean to show a dismiss action in the Snackbar. This is
         * recommended to be set to true better accessibility when a Snackbar is set with a
         * [SnackbarDuration.Indefinite].
         * @param duration duration of the Snackbar.
         * @param onSnackbarResult A callback for when the snackbar is dismissed or the action is performed.
         * @return a [Text] instance of [SnackbarMessage].
         */
        fun from(
            userMessage: UserMessage,
            actionLabelMessage: UserMessage? = null,
            withDismissAction: Boolean = false,
            duration: SnackbarDuration = SnackbarDuration.Short,
            onSnackbarResult: (SnackbarResult) -> Unit = {},
        ) = Text(
            userMessage = userMessage,
            actionLabelMessage = actionLabelMessage,
            withDismissAction = withDismissAction,
            duration = duration,
            onSnackbarResult = onSnackbarResult
        )

        /**
         * Returns a new [SnackbarMessage.Visuals] instance.
         *
         * @param snackbarVisuals Custom visuals for the snackbar.
         * @param onSnackbarResult A callback for when the snackbar is dismissed or the action is performed.
         * @return a [Visuals] instance of [SnackbarMessage].
         */
        fun from(
            snackbarVisuals: SnackbarVisuals,
            onSnackbarResult: (SnackbarResult) -> Unit,
        ) = Visuals(snackbarVisuals = snackbarVisuals, onSnackbarResult = onSnackbarResult)
    }
}

/**
 * A sealed interface for defining user messages that can be displayed in the UI.
 */
sealed interface UserMessage {
    /**
     * A data class that represents a user message as a simple text string.
     *
     * @property value The text string value of the user message.
     */
    data class Text(val value: String) : UserMessage

    /**
     * A class that represents a user message as a string resource with optional format arguments.
     *
     * @property resId The resource ID of the string resource for the user message.
     * @property formatArgs Optional format arguments for the string resource.
     */
    class StringResource(@StringRes val resId: Int, vararg val formatArgs: Any) : UserMessage

    companion object {
        /**
         * Returns a [UserMessage.Text] object with the given text [value].
         *
         * @param value The text string value of the user message.
         * @return A new instance of [UserMessage.Text] with the given text [value].
         */
        fun from(value: String) = Text(value = value)

        /**
         * Returns a [UserMessage.StringResource] object with the given string resource [resId] and
         * optional format arguments.
         *
         * @param resId The resource ID of the string resource for the user message.
         * @param formatArgs Optional format arguments for the string resource.
         * @return A new instance of [UserMessage.StringResource] with the given string resource
         * [resId] and optional format arguments.
         */
        fun from(@StringRes resId: Int, vararg formatArgs: Any) =
            StringResource(resId = resId, formatArgs = formatArgs)
    }
}

/**
 * Returns a [String] representation of this [UserMessage] object.
 */
@Composable
fun UserMessage.asString() = when (this) {
    is UserMessage.Text -> value
    is UserMessage.StringResource -> stringResource(id = resId, formatArgs = formatArgs)
}