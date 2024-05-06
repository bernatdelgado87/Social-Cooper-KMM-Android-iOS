package app.mistercooper.social.ui.common.components

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import app.mistercooper.social.R
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CommonScaffoldTopBar(navController: NavController, topBarTitle: String, showError: Boolean, content: @Composable (modifier: Modifier) -> Unit, actions: @Composable RowScope.() -> Unit = {}){
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }
    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },
        topBar = {
            TopAppBar(
                title = { Text(text = topBarTitle) },
                navigationIcon = if (navController.previousBackStackEntry != null) {
                    {
                        IconButton(onClick = { navController.navigateUp() }) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Outlined.ArrowBack,
                                contentDescription = stringResource(R.string.back)
                            )
                        }
                    }
                } else {
                    {
                        //nothing to do
                    }
                },
                actions = actions

            )
        },
        content = { padding ->
            content(modifier = Modifier.padding(padding))
        }
    )
    if (showError) {
        val errorMessage = stringResource(R.string.error_message)
        LaunchedEffect(Unit) {
            scope.launch {
                snackbarHostState.showSnackbar(errorMessage)
            }
        }
    }
}