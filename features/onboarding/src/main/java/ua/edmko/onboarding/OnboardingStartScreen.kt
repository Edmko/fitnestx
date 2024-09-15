package ua.edmko.onboarding

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ua.edmko.core_ui.R.drawable
import ua.edmko.core_ui.components.TextButton
import ua.edmko.core_ui.theme.AppTheme
import ua.edmko.onboarding.components.FloatingButtonWithProgress

private const val START_SCREEN_INDEX = 0

@Composable
fun OnboardingStartScreen(toSignUp: () -> Unit) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        var screenState by remember { mutableIntStateOf(START_SCREEN_INDEX) }
        AnimatedContent(
            targetState = screenState,
            label = "AnimatedContent",
            transitionSpec = { slideInHorizontally { it } togetherWith slideOutHorizontally { -it } }
        ) { state ->
            when (state) {
                START_SCREEN_INDEX -> OnBoardingStartContent { screenState++ }
                in FRAMES_RANGE -> OnboardingFeaturesScreen(frame = frames[state - 1])
                else -> toSignUp()
            }
        }
        if (screenState in FRAMES_RANGE) {
            val progress by remember { derivedStateOf { screenState / frames.size.toFloat() } }
            val progressAnimation by animateFloatAsState(targetValue = progress, label = "")
            FloatingButtonWithProgress(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(bottom = 30.dp, end = 30.dp)
                    .size(60.dp),
                progress = progressAnimation,
                painter = painterResource(id = drawable.ic_arrow_right),
                onClick = { screenState++ }
            )
        }
    }
}

@Composable
private fun OnBoardingStartContent(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .navigationBarsPadding()
    ) {
        Column(
            modifier = Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = appNameTitle)
            Text(text = stringResource(R.string.onboarding_start_subtitle), style = AppTheme.typography.subtitleRegular)
        }
        TextButton(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(start = 30.dp, end = 30.dp, bottom = 16.dp)
                .fillMaxWidth()
                .height(60.dp),
            text = stringResource(R.string.start_button_title),
            onClick = onClick
        )
    }
}

@Composable
private fun OnboardingFeaturesScreen(
    modifier: Modifier = Modifier,
    frame: Frame,
) {
    Column(modifier = modifier) {
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f),
            contentScale = ContentScale.Crop,
            painter = painterResource(id = frame.frame),
            contentDescription = null
        )
        Text(
            modifier = Modifier.padding(start = 30.dp, end = 30.dp, top = 40.dp),
            text = stringResource(id = frame.title),
            style = AppTheme.typography.h2Bold
        )
        Text(
            modifier = Modifier.padding(start = 30.dp, end = 30.dp, top = 15.dp),
            text = stringResource(id = frame.description),
            style = AppTheme.typography.mediumRegular
        )
    }
}


private val appNameTitle: AnnotatedString
    @Composable
    @ReadOnlyComposable
    get() = buildAnnotatedString {
        append(stringResource(id = R.string.onboarding_start_title))
        addStyle(
            SpanStyle(
                color = AppTheme.colorScheme.textColor,
                fontSize = 36.sp,
                fontWeight = FontWeight.W700
            ),
            start = 0,
            end = this.length - 1
        )
        addStyle(
            SpanStyle(
                brush = AppTheme.colorScheme.brandGradient,
                fontSize = 50.sp,
                fontWeight = FontWeight.W700
            ),
            start = this.length - 1,
            end = this.length
        )
    }

private data class Frame(
    @DrawableRes val frame: Int,
    @StringRes val title: Int,
    @StringRes val description: Int,
)

private val frames = listOf(
    Frame(R.drawable.ic_frame_1, R.string.frame_1_title, R.string.frame_1_description),
    Frame(R.drawable.ic_frame_2, R.string.frame_2_title, R.string.frame_2_description),
    Frame(R.drawable.ic_frame_3, R.string.frame_3_title, R.string.frame_3_description),
    Frame(R.drawable.ic_frame_4, R.string.frame_4_title, R.string.frame_4_description)
)
private val FRAMES_RANGE = 1..frames.size