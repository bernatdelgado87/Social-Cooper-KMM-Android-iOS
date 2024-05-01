package app.mistercooper.social

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import app.mistercooper.social.ui.theme.SocialCooperAndroidTheme
import app.mistercooper.social.ui.theme.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    val viewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SocialCooperAndroidTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background

                ) {
                    Greeting()
                }
            }
        }
    }
}

@Composable
fun Greeting(modifier: Modifier = Modifier) {
    val state = hiltViewModel<HomeViewModel>().homeUiModel.collectAsState()
    if (state.value.isLoading){
        Text(
            text = "Is Loading",
            modifier = modifier
        )
    }

    if (state.value.isError){
        Text(
            text = "Is Error",
            modifier = modifier
        )
    }

    if (state.value.posts?.isEmpty() == false){
        Text(
            text = "Is List",
            modifier = modifier
        )
    } else if (state.value.posts.isNullOrEmpty()){
        Text(text = "is empty or null")
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SocialCooperAndroidTheme {
        Greeting()
    }
}