package app.mistercooper.social.ui.common.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import app.mistercooper.social.R
import app.mistercooper.social.ui.common.navigation.NavigationRoute
import app.mistercooper.social.ui.common.navigation.navigate
import app.mistercooper.social.ui.feature.home.ErrorComponent
import app.mistercooper.social.ui.feature.home.HomeFeedView
import app.mistercooper.social.ui.feature.home.viewmodel.HomeViewModel
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

@Composable
fun CommonScaffoldBottomBar(navController: NavController, content: @Composable (modifier: Modifier) -> Unit, showError: Boolean, actionFloatingButton: () -> Unit, iconVectorFloatingButton: ImageVector) {
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }
    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },
        floatingActionButton = {
            Box() {
                FloatingActionButton(
                    onClick = { actionFloatingButton() },
                    shape = CircleShape,
                    modifier = Modifier
                        .align(Alignment.Center)
                        .size(80.dp)
                ) {
                    Icon(
                        imageVector = iconVectorFloatingButton,
                        contentDescription = "add",
                        modifier = Modifier.size(45.dp)
                    )
                }
            }
        },
        floatingActionButtonPosition = FabPosition.End,
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