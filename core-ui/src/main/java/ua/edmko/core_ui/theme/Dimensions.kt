package ua.edmko.core_ui.theme

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class Dimensions(
    val micro: Dp = 4.dp,
    val microL: Dp = 5.dp,
    val small: Dp = 10.dp,
    val normal: Dp = 15.dp,
    val normalL: Dp = 16.dp,
    val big: Dp = 20.dp,
    val large: Dp = 30.dp,
) {

    val horizontalEdge: Dp = large
    val verticalEdge: Dp = normalL
}