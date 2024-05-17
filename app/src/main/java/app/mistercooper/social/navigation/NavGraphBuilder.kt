package app.mistercooper.social.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import app.mistercooper.ui.common.navigation.GlobalNavigator
import app.mistercooper.ui.common.navigation.NavigationRoute.HOME_FEED
import app.mistercooper.ui.common.navigation.NavigationRoute.LOGIN
import app.mistercooper.ui.common.navigation.NavigationRoute.LOGIN_OR_REGISTER
import app.mistercooper.ui.common.navigation.NavigationRoute.PUBLISH_NOW
import app.mistercooper.ui.common.navigation.NavigationRoute.REGISTER
import app.mistercooper.ui.home.HomeScreen
import app.mistercooper.ui.publish.PublishHomeScreen
import app.mistercooper.ui.registerlogin.LoginOrRegisterScreen
import app.mistercooper.ui.registerlogin.LoginScreen
import app.mistercooper.ui.registerlogin.RegisterScreen

fun NavGraphBuilder.getNavGraphBuilder(globalNavigator: GlobalNavigator) = run {
    composable(HOME_FEED.name) {
        HomeScreen(globalNavigator = globalNavigator)
    }
    composable(PUBLISH_NOW.name) {
        PublishHomeScreen(
            globalNavigator = globalNavigator
        )
    }
    composable(LOGIN_OR_REGISTER.name) {
        LoginOrRegisterScreen(
            globalNavigator = globalNavigator
        )
    }
    composable(REGISTER.name) {
        RegisterScreen(
            globalNavigator = globalNavigator
        )
    }
    composable(LOGIN.name) {
        LoginScreen(
            globalNavigator = globalNavigator
        )
    }
}

