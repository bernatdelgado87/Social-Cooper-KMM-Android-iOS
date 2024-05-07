package app.mistercooper.social.ui.feature.registerlogin

import android.content.Intent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import app.mistercooper.social.ui.common.components.CustomTextField
import app.mistercooper.social.ui.common.components.LoadingComponent
import app.mistercooper.social.ui.common.utils.findActivity
import app.mistercooper.social.ui.feature.main.MainActivity
import app.mistercooper.social.ui.feature.registerlogin.viewmodel.RegisterLoginViewModel

@Composable
fun RegisterScreen(navController: NavController) {
    val viewModel = hiltViewModel<RegisterLoginViewModel>()
    val state = viewModel.registerLoginState.collectAsState()

    var userName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    if (state.value.registerLoginSuccess) {
        //todo find a better solution for reset activity
        val context = LocalContext.current
        context.startActivity(Intent(context, MainActivity::class.java))
        context.findActivity()?.finish()
    }

    Box(modifier = Modifier.fillMaxSize()) {

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.fillMaxHeight(0.2f))
            Text(
                modifier = Modifier.padding(20.dp),
                text = "¡Regístrate ahora!",
                style = MaterialTheme.typography.headlineLarge
            )

            CustomTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentSize(align = Alignment.BottomStart, unbounded = false)
                    .padding(horizontal = 8.dp),
                onTextChanged = { newText ->
                    userName = newText
                },
                showKeyboard = false,
                stringResource(id = R.string.register_name),
                singleLine = false
            )

            CustomTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentSize(align = Alignment.BottomStart, unbounded = false)
                    .padding(horizontal = 8.dp),
                onTextChanged = { newText ->
                    email = newText
                },
                showKeyboard = false,
                stringResource(id = R.string.register_email),
                singleLine = false
            )

            CustomTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentSize(align = Alignment.BottomStart, unbounded = false)
                    .padding(horizontal = 8.dp),
                onTextChanged = { newText ->
                    password = newText
                },
                showKeyboard = false,
                stringResource(id = R.string.register_password),
                singleLine = false
            )

            Button(
                modifier = Modifier
                    .padding(20.dp)
                    .fillMaxWidth(),
                onClick = {
                    viewModel.registerUser(
                        email = email,
                        userName = userName,
                        password = password
                    )
                }) {
                Text(text = "Registrarse")
            }
            if (state.value.loading) {
                LoadingComponent()
            }
        }
    }
}