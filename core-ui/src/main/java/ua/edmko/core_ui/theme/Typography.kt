package ua.edmko.core_ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import ua.edmko.core_ui.R

val Poppins = FontFamily(
    Font(R.font.poppins_bold, FontWeight.W700),
    Font(R.font.poppins_medium, FontWeight.W500),
    Font(R.font.poppins_regular, FontWeight.W400),
    Font(R.font.poppins_semibold, FontWeight.W600),
)

@Immutable
data class Typography(

    val buttonText: TextStyle = TextStyle.Default.copy(
        fontFamily = Poppins,
        fontWeight = FontWeight.W700,
        fontSize = 16.sp,
        lineHeight = 24.sp,
    ),
    val h2Bold: TextStyle = TextStyle.Default.copy(
        fontFamily = Poppins,
        fontWeight = FontWeight.W700,
        fontSize = 24.sp,
        lineHeight = 36.sp,
    ),
    val h4Bold: TextStyle = TextStyle.Default.copy(
        fontFamily = Poppins,
        fontWeight = FontWeight.W700,
        fontSize = 20.sp,
        lineHeight = 30.sp,
    ),
    val subtitleRegular: TextStyle = TextStyle.Default.copy(
        fontFamily = Poppins,
        fontWeight = FontWeight.W400,
        fontSize = 18.sp,
        lineHeight = 27.sp,
    ),
    val largeRegular: TextStyle = TextStyle.Default.copy(
        fontFamily = Poppins,
        fontWeight = FontWeight.W400,
        fontSize = 16.sp,
        lineHeight = 24.sp,
    ),
    val mediumRegular: TextStyle = TextStyle.Default.copy(
        fontFamily = Poppins,
        fontWeight = FontWeight.W400,
        fontSize = 14.sp,
        lineHeight = 21.sp,
    ),
    val mediumMedium: TextStyle = TextStyle.Default.copy(
        fontFamily = Poppins,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        lineHeight = 21.sp,
    ),
    val smallRegular: TextStyle = TextStyle.Default.copy(
        fontFamily = Poppins,
        fontWeight = FontWeight.W400,
        fontSize = 12.sp,
        lineHeight = 18.sp,
    ),
)

val AppTypography = Typography()