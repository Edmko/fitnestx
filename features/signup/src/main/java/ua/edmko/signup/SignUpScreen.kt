package ua.edmko.signup

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Checkbox
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.TextLinkStyles
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withLink
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import ua.edmko.core_ui.components.EditText
import ua.edmko.core_ui.components.TextButton
import ua.edmko.core_ui.controllers.SnackbarMessageHandler
import ua.edmko.core_ui.theme.AppTheme
import ua.edmko.core_ui.theme.checkboxColors

@Composable
fun SignUpScreen() {
    val viewModel: SignUpViewModel = hiltViewModel()
    val state by viewModel.viewStates.collectAsStateWithLifecycle()
    state?.let {
        SnackbarMessageHandler(
            snackbarMessage = it.snackbarMessage,
            onDismissSnackbar = { viewModel.obtainEvent(DismissSnackbar) }
        )
        SignUpContent(state = it, events = viewModel::obtainEvent)
    }
}

@Composable
private fun SignUpContent(
    modifier: Modifier = Modifier,
    state: SignUpViewState,
    events: (SignUpEvent) -> Unit,
) {

    Column(
        modifier = modifier
            .fillMaxSize()
            .systemBarsPadding()
            .background(AppTheme.colorScheme.background)
            .padding(horizontal = AppTheme.dimensions.horizontalEdge),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            modifier = Modifier.padding(top = AppTheme.dimensions.verticalEdge),
            text = stringResource(R.string.sign_up_greeting_text),
            style = AppTheme.typography.largeRegular
        )

        Text(
            modifier = Modifier.padding(top = AppTheme.dimensions.microL),
            text = stringResource(R.string.sign_up_title),
            style = AppTheme.typography.h4Bold)

        EditText(
            modifier = Modifier
                .padding(top = 42.dp)
                .fillMaxWidth(),
            labelText = stringResource(R.string.sign_up_first_name_hint),
            model = state.firstName,
            onValueChanged = { events(FirstNameChanges(it)) },
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next, keyboardType = KeyboardType.Text),
            leadingIconPainter = painterResource(id = ua.edmko.core_ui.R.drawable.ic_profile),
        )

        EditText(
            modifier = Modifier
                .padding(top = AppTheme.dimensions.micro)
                .fillMaxWidth(),
            labelText = stringResource(R.string.sign_up_last_name_hint),
            model = state.lastName,
            onValueChanged = { events(LastNameChanges(it)) },
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next, keyboardType = KeyboardType.Text),
            leadingIconPainter = painterResource(id = ua.edmko.core_ui.R.drawable.ic_profile),
        )

        EditText(
            modifier = Modifier
                .padding(top = AppTheme.dimensions.normal)
                .fillMaxWidth(),
            labelText = stringResource(R.string.signup_email_hint),
            model = state.email,
            onValueChanged = { events(EmailChanges(it)) },
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next, keyboardType = KeyboardType.Email),
            leadingIconPainter = painterResource(id = ua.edmko.core_ui.R.drawable.ic_message),
        )

        EditText(
            modifier = Modifier
                .padding(top = AppTheme.dimensions.normal)
                .fillMaxWidth(),
            labelText = stringResource(R.string.sign_up_password_hint),
            model = state.password,
            onValueChanged = { events(PasswordChanges(it)) },
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done, keyboardType = KeyboardType.Password),
            leadingIconPainter = painterResource(id = ua.edmko.core_ui.R.drawable.ic_lock),
        )

        Checkbox(
            checked = state.isPrivacyAccepted,
            modifier = Modifier
                .padding(top = AppTheme.dimensions.small)
                .align(Alignment.Start),
            events = events
        )

        Spacer(modifier = Modifier.weight(1f))


        TextButton(
            modifier = Modifier
                .padding(bottom = AppTheme.dimensions.normalL)
                .fillMaxWidth()
                .height(60.dp),
            text = stringResource(R.string.sign_up_button_caption),
            onClick = { events(RegisterClicks) }
        )

        LoginDivider(modifier = Modifier.padding(top = AppTheme.dimensions.big))

        SideLogin(modifier = Modifier.padding(top = AppTheme.dimensions.big), events = events)

        HaveAccountText(
            modifier = Modifier.padding(top = AppTheme.dimensions.large, bottom = AppTheme.dimensions.verticalEdge),
            onClick = { events(LoginClicks) }
        )
    }
}

@Composable
private fun Checkbox(
    modifier: Modifier = Modifier,
    checked: Boolean,
    events: (SignUpEvent) -> Unit,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(
            colors = AppTheme.colorScheme.checkboxColors,
            checked = checked,
            onCheckedChange = { events(PrivacyCheckChanges(checked.not())) }
        )
        val text = buildAnnotatedString {
            CheckboxText(text = stringResource(id = R.string.by_continuing_you_accept_our))
            CheckboxLink(text = stringResource(id = R.string.privacy_policy)) { events(PrivacyClicks) }
            CheckboxText(text = stringResource(id = R.string.and))
            CheckboxLink(text = stringResource(id = R.string.terms_of_use)) { events(TermsClicks) }
        }
        Text(text = text, modifier.padding(top = 2.dp))
    }
}

@Composable
private fun AnnotatedString.Builder.CheckboxLink(text: String, onClick: () -> Unit) = withLink(
    link = LinkAnnotation.Clickable(
        tag = text,
        styles = TextLinkStyles(
            style = AppTheme.typography.smallRegular.toSpanStyle().copy(
                color = AppTheme.colorScheme.minorGrayMedium,
                textDecoration = TextDecoration.Underline
            )
        ),
        linkInteractionListener = { onClick() }
    ),
    block = { append(text) }
)

@Composable
private fun AnnotatedString.Builder.CheckboxText(text: String) = withStyle(
    style = AppTheme.typography.smallRegular.toSpanStyle().copy(
        color = AppTheme.colorScheme.minorGrayMedium
    ),
    block = { append(text) }
)

@Composable
private fun LoginDivider(
    modifier: Modifier,
) {
    Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
        HorizontalDivider(modifier = Modifier.weight(1f))
        Text(
            text = stringResource(R.string.signup_or),
            modifier = Modifier.padding(horizontal = AppTheme.dimensions.small), style = AppTheme.typography.smallRegular
        )
        HorizontalDivider(modifier = Modifier.weight(1f), color = AppTheme.colorScheme.minorGrayLight)
    }
}

@Composable
private fun SideLogin(
    modifier: Modifier = Modifier,
    events: (SignUpEvent) -> Unit,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center
    ) {
        SideLoginButton(
            onClick = { events(GoogleClicks) },
            painter = painterResource(id = R.drawable.ic_google)
        )
        Spacer(modifier = Modifier.width(30.dp))
        SideLoginButton(
            onClick = { events(FacebookClicks) },
            painter = painterResource(id = R.drawable.ic_facebook)
        )
    }
}

@Composable
private fun SideLoginButton(
    modifier: Modifier = Modifier,
    painter: Painter,
    onClick: () -> Unit,
) {
    Box(
        modifier = modifier
            .size(50.dp)
            .clip(AppTheme.shapes.large)
            .border(1.dp, color = AppTheme.colorScheme.minorGrayLight, shape = AppTheme.shapes.large)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                role = Role.Button,
                indication = ripple(),
                onClick = onClick
            )
    ) {
        Image(
            painter = painter,
            contentDescription = null,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

@Composable
private fun HaveAccountText(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    val text = buildAnnotatedString {
        withStyle(
            AppTheme.typography.mediumRegular.toSpanStyle().copy(
                color = AppTheme.colorScheme.textColor
            )
        ) {
            append(stringResource(R.string.sign_up_already_have_an_account))
        }
        append(" ")
        withLink(
            LinkAnnotation.Clickable(
                tag = "login",
                styles = TextLinkStyles(
                    style = AppTheme.typography.mediumMedium.toSpanStyle().copy(
                        brush = AppTheme.colorScheme.horizontalSecondaryGradient
                    )
                ),
            ) {
                onClick()
            }
        ) {
            append(stringResource(R.string.sign_up_login_button_title))
        }
    }
    Text(text = text, modifier = modifier)
}

@Preview
@Composable
private fun LoginScreenPreview() {
    AppTheme {
        SignUpContent(modifier = Modifier.fillMaxSize(), state = SignUpViewState()) {}
    }
}