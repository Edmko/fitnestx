package ua.edmko.core_ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp

data class Shapes(
    val small: Shape,
    val medium: Shape,
    val large: Shape,
    val full: Shape,
)

val AppShapes = Shapes(
    small = RoundedCornerShape(4.dp),
    medium = RoundedCornerShape(10.dp),
    large = RoundedCornerShape(16.dp),
    full = RoundedCornerShape(50.dp)
)
