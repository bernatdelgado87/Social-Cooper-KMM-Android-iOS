package app.mistercooper.social.activity

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
import app.mistercooper.ui.common.navigation.GlobalNavigator
import app.mistercooper.social.navigation.getNavGraphBuilder
import app.mistercooper.social.theme.SocialCooperAndroidTheme
import app.mistercooper.ui.common.components.LoadingComponent
import app.mistercooper.ui.common.navigation.CustomNavigator
import app.mistercooper.ui.common.navigation.NavigationRoute
import app.mistercooper.ui.common.viewModel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var customNavigator: CustomNavigator
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SocialCooperAndroidTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val nativeNavController = rememberNavController()

                    val viewModel = hiltViewModel<MainViewModel>()
                    val state = viewModel.isRgisteredState.collectAsState()

                    state.value?.let { isRegistered ->
                        NavHost(
                            navController = nativeNavController,
                            startDestination = if (isRegistered) {
                                NavigationRoute.HOME_FEED.name
                            } else {
                                NavigationRoute.LOGIN_OR_REGISTER.name
                            }
                        ) {
                            getNavGraphBuilder(
                                GlobalNavigator(
                                nativeController = nativeNavController,
                                customNavigator = customNavigator
                            )
                            )
                        }
                    } ?: LoadingComponent()
                }
            }
        }
    }
}









