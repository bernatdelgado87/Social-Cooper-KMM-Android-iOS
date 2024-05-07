package app.mistercooper.social.ui.common.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import app.mistercooper.social.ui.feature.home.HomeScreen
import app.mistercooper.social.ui.feature.publish.PublishHomeScreen
import app.mistercooper.social.ui.feature.registerlogin.LoginOrRegisterScreen
import app.mistercooper.social.ui.feature.registerlogin.LoginScreen
import app.mistercooper.social.ui.feature.registerlogin.RegisterScreen

fun NavGraphBuilder.getNavGraphBuilder(navigationController: NavHostController) = run {
    composable(NavigationRoute.HOME_FEED.name) {
        HomeScreen(navController = navigationController)
    }
    composable(NavigationRoute.PUBLISH_NOW.name) {
        PublishHomeScreen(
            navController = navigationController
        )
    }
    composable(NavigationRoute.LOGIN_OR_REGISTER.name) {
        LoginOrRegisterScreen(
            navController = navigationController
        )
    }
    composable(NavigationRoute.REGISTER.name) {
        RegisterScreen(
            navController = navigationController
        )
    }
    composable(NavigationRoute.LOGIN.name) {
        LoginScreen(
            navController = navigationController
        )
    }
}

fun NavController.navigate(route: NavigationRoute){
    navigate(route.name)
}