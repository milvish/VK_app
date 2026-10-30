package com.lizina.vkapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.lizina.vkapp.presentation.appdetails.AppDetailsScreen
import com.lizina.vkapp.presentation.appdetails.AppListScreen
import kotlinx.serialization.Serializable


@Composable
fun AppNavHost() {

    val navController = rememberNavController()


    NavHost(
        navController = navController,
        startDestination = Destination.AppListDestination
    ){
        composable<Destination.AppListDestination>{ AppListScreen(navController = navController) }
        composable<Destination.AppDetailsContentDestination> { backStackEntry ->
            val routeInfo = backStackEntry.toRoute<Destination.AppDetailsContentDestination>()
            AppDetailsScreen(
                navController = navController,
                userId = routeInfo.userId
            )

        }
    }
}


sealed class Destination {
    @Serializable
    object AppListDestination : Destination()
    @Serializable
    class AppDetailsContentDestination(val userId: String) : Destination()
}


