package com.grantham.showplace.android.navigation

import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.grantham.showplace.android.showDetails.ShowDetailsRoute
import com.grantham.showplace.android.showFeed.ShowListRoute

@Composable
fun ShowPlaceNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    startDestination: String = ShowFeedNavigation.route,
) {
    NavHost(
        navController = navController,
        modifier = modifier,
        startDestination = startDestination
    ) {
        composable(route = ShowFeedNavigation.route) {
            ShowListRoute() { show ->
                navController.navigate(ShowDetailsNavigation.createNavigationRoute(show.id))
            }
        }
        composable(
            route = ShowDetailsNavigation.route,
            arguments = listOf(
                navArgument(ShowDetailsNavigation.showArg) { type = NavType.StringType }
            )
        ) {
            ShowDetailsRoute()
        }
    }
}

object ShowFeedNavigation : ShowPlaceNavigationDestination {
    override val route = "show_feed"
}

object ShowDetailsNavigation : ShowPlaceNavigationDestination {
    fun createNavigationRoute(showArg: String): String {
        val encodedId = Uri.encode(showArg)
        return "show_details/$encodedId"
    }

    val showArg = "show"
    override val route = "show_details/{$showArg}"
}