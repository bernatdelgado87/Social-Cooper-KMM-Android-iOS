package app.mistercooper.social.ui.feature.registerlogin

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import app.mistercooper.social.ui.common.navigation.NavigationRoute.LOGIN
import app.mistercooper.social.ui.common.navigation.NavigationRoute.REGISTER
import app.mistercooper.social.ui.common.navigation.navigate

@Composable
fun LoginScreen(navController: NavController) {

    Box(modifier = Modifier.fillMaxSize()){
        Column(modifier = Modifier.fillMaxSize()) {
            Text(text = "¡Regístrate ahora!", style = MaterialTheme.typography.headlineLarge)
            Button(onClick = { navController.navigate(REGISTER) }) {
                Text(text = "Registrarse")
            }
            Button(onClick = { navController.navigate(LOGIN) }) {
                Text(text = "Login")
            }
        }
    }
}