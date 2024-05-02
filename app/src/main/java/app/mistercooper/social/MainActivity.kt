package app.mistercooper.social

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import app.mistercooper.social.ui.home.HomeScreen
import app.mistercooper.social.ui.home.viewmodel.HomeViewModel
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
                    val state = hiltViewModel<HomeViewModel>().homeUiModel.collectAsState()
                    HomeScreen(state.value.posts)
                    if (state.value.isLoading) {
                        Text(
                            text = "Is Loading",
                        )
                    }
                    if (state.value.isError) {
                        Text(
                            text = "Is Error",
                        )
                    }
                }
            }
        }
    }
}

