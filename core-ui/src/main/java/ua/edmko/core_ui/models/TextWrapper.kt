package ua.edmko.core_ui.models

import android.content.Context
import android.util.Log
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource

sealed interface TextWrapper {

    companion object {

        fun from(value: String) = Text(value)

        fun from(@StringRes res: Int, vararg formatArgs: Any) = StringResource(res, *formatArgs)
    }

    data class Text(val text: String) : TextWrapper
    class StringResource(@StringRes val res: Int, vararg val arguments: Any) : TextWrapper

    @Composable
    fun unwrap(): String = when (this) {
        is Text -> text
        is StringResource -> stringResource(id = res, *arguments)
    }
}