package app.mistercooper.social.ui.feature.registerlogin

import android.content.Intent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import app.mistercooper.social.R
import app.mistercooper.social.ui.common.components.CommonScaffoldTopBar
import app.mistercooper.social.ui.common.components.CustomTextField
import app.mistercooper.social.ui.common.components.LoadingComponent
import app.mistercooper.social.ui.common.components.TextType
import app.mistercooper.social.ui.common.utils.findActivity
import app.mistercooper.social.ui.feature.main.MainActivity
import app.mistercooper.social.ui.feature.registerlogin.viewmodel.RegisterLoginViewModel

@Composable
fun LoginScreen(navController: NavController) {
    val viewModel = hiltViewModel<RegisterLoginViewModel>()
    val state = viewModel.registerLoginState.collectAsState()

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    if (state.value.registerLoginSuccess) {
        //todo find a better solution for reset activity
        val context = LocalContext.current
        context.startActivity(Intent(context, MainActivity::class.java))
        context.findActivity()?.finish()
    }

    CommonScaffoldTopBar(
        navController = navController,
        topBarTitle = stringResource(id = R.string.publish_now_title),
        content = { modifier ->


            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .imePadding()
            ) {

                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        modifier = Modifier.padding(20.dp),
                        text = stringResource(R.string.login_now_title),
                        style = MaterialTheme.typography.headlineLarge
                    )

                    CustomTextField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentSize(align = Alignment.BottomStart, unbounded = false)
                            .padding(horizontal = 8.dp, vertical = 10.dp),
                        onTextChanged = { newText ->
                            email = newText
                        },
                        showKeyboard = false,
                        placeholderText = stringResource(id = R.string.register_email),
                        type = TextType.EMAIL
                    )

                    CustomTextField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentSize(align = Alignment.BottomStart, unbounded = false)
                            .padding(horizontal = 8.dp, vertical = 10.dp),
                        onTextChanged = { newText ->
                            password = newText
                        },
                        showKeyboard = false,
                        placeholderText = stringResource(id = R.string.register_password),
                        type = TextType.PASSWORD
                    )
                    Button(
                        modifier = Modifier
                            .padding(20.dp)
                            .fillMaxWidth(),
                        onClick = {
                            viewModel.login(
                                email = email,
                                password = password
                            )
                        }) {
                        Text(text = stringResource(R.string.login_button))
                    }
                    if (state.value.loading) {
                        LoadingComponent()
                    }
                }
            }
        },
        showError = state.value.error
    )
}
