package app.mistercooper.social.ui.feature.registerlogin

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import app.mistercooper.social.R
import app.mistercooper.social.ui.common.navigation.NavigationRoute.LOGIN
import app.mistercooper.social.ui.common.navigation.NavigationRoute.REGISTER
import app.mistercooper.social.ui.common.navigation.navigate
import coil.compose.AsyncImage

@Composable
fun LoginOrRegisterScreen(navController: NavController) {

    Box(modifier = Modifier.fillMaxSize()){

        Column(modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
            ) {
            Spacer(modifier = Modifier.fillMaxHeight(0.2f))
            Text(
                modifier = Modifier.padding(20.dp),
                text = stringResource(R.string.register_now_title),
                style = MaterialTheme.typography.headlineLarge)

            AsyncImage(modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
                model = R.drawable.background_social,
                contentDescription = "")


            Button(
                modifier = Modifier
                    .padding(20.dp)
                    .fillMaxWidth(),
                onClick = { navController.navigate(REGISTER) }) {
                Text(text = "Registrarse")
            }
            OutlinedButton(
                modifier = Modifier
                    .padding(horizontal = 20.dp)
                    .fillMaxWidth(),
                onClick = { navController.navigate(LOGIN) }) {
                Text(text = "Login")
            }
        }
    }
}