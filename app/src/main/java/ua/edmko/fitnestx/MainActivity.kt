package ua.edmko.fitnestx

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import ua.edmko.core_ui.theme.AppTheme
import ua.edmko.core_ui.controllers.ProvideSnackbarController
import ua.edmko.signup.SignUpScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppTheme {
                val snackbarHostState = remember { SnackbarHostState() }
                val coroutineScope = rememberCoroutineScope()
                ProvideSnackbarController(
                    snackbarHostState = snackbarHostState,
                    coroutineScope = coroutineScope
                ) {
                    Scaffold(
                        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
                        modifier = Modifier.fillMaxSize()
                    ) { paddings ->
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(AppTheme.colorScheme.background)
                                .padding(paddings),
                        ) {
                            SignUpScreen()
                        }
                    }
                }
            }
        }
    }
}