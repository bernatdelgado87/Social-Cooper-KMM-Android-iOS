package app.mistercooper.social.ui.common.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import app.mistercooper.social.ui.feature.home.HomeScreen
import app.mistercooper.social.ui.feature.publish.PublishHomeScreen

fun NavGraphBuilder.getNavGraphBuilder(navigationController: NavHostController) = run {
    composable(NavigationRoute.HOME_SCREEN.name) {
        HomeScreen(navController = navigationController)
    }
    composable(NavigationRoute.PUBLISH_HOME.name) {
        PublishHomeScreen(
            navController = navigationController
        )
    }
}

fun NavController.navigate(route: NavigationRoute){
    navigate(route.name)
}