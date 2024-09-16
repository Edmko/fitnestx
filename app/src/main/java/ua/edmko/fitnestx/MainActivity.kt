package ua.edmko.fitnestx

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import ua.edmko.core.NavigationManager
import ua.edmko.core_ui.controllers.ProvideSnackbarController
import ua.edmko.core_ui.theme.AppTheme
import ua.edmko.navigation.NavigationComponent
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var navigationManager: NavigationManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            AppTheme {
                val snackbarHostState = remember { SnackbarHostState() }
                val coroutineScope = rememberCoroutineScope()
                ProvideSnackbarController(
                    snackbarHostState = snackbarHostState,
                    coroutineScope = coroutineScope,
                ) {
                    Scaffold(
                        snackbarHost = { SnackbarHost(modifier = Modifier.imePadding(), hostState = snackbarHostState) },
                        modifier = Modifier.fillMaxSize(),
                        containerColor = AppTheme.colorScheme.background,
                        contentWindowInsets = WindowInsets(0)
                    ) { paddings ->
                        NavigationComponent(modifier = Modifier.padding(paddings), navController, navigationManager)
                    }
                }
            }
        }
    }
}