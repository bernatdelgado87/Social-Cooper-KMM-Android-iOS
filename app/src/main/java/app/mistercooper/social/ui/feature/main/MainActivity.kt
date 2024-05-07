package app.mistercooper.social.ui.feature.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import app.mistercooper.social.ui.common.components.LoadingComponent
import app.mistercooper.social.ui.common.navigation.NavigationRoute
import app.mistercooper.social.ui.common.navigation.getNavGraphBuilder
import app.mistercooper.social.ui.theme.SocialCooperAndroidTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SocialCooperAndroidTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navigationController = rememberNavController()
                    val viewModel = hiltViewModel<MainViewModel>()
                    val state = viewModel.isRgisteredState.collectAsState()

                    state.value.isRegistered?.let { isRegistered ->
                        NavHost(
                            navController = navigationController,
                            startDestination = if (isRegistered) {
                                NavigationRoute.HOME_FEED.name
                            } else {
                                NavigationRoute.LOGIN_OR_REGISTER.name
                            }
                        ) {
                            getNavGraphBuilder(navigationController)
                        }
                    } ?: LoadingComponent()
                }
            }
        }
    }
}








