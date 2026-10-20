package com.lizina.vkapp

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


/*
            AppDetailsContent(
                navController = navController,
                content = AppDetailsState.Content(
                    appDetails = AppDetails(
                        id = "b1e4a8c3-9012-4def-7745-32f9a1b8c7d6",
                        name = "Погода",
                        developer = "Utilities Team",
                        category = Category.UTILITIES, // или выбери подходящую категорию
                        ageRating = 3,
                        size = 8.2f,
                        iconUrl = "https://static.rustore.ru/imgproxy/g_FEnTsf6hvUd49Q4-7P4wj-cvdBE-40V2zNVpQPz5E/preset:web_app_icon_62/plain/https://static.rustore.ru/apk/937663/content/ICON/4984fd0f-f97c-4e73-85e5-474880cce068.png@webp",
                        screenshotUrlList = listOf(
                            "https://static.rustore.ru/imgproxy/0A8_oixnJofAo_60pmqp4LqPeWVwCTWYJBMADFIb5k4/preset:web_scr_prt_162/plain/https://static.rustore.ru/2026/2/3/86/apk/937663/content/SCREENSHOT/fb182c35-0f45-47a4-bda8-74d512e8d5bb.png@webp",
                            "https://static.rustore.ru/imgproxy/OINr_ezpIq97TvjgUmj2z1TBZ-NdG_UurMD_RfaC7oo/preset:web_scr_prt_162/plain/https://static.rustore.ru/2026/2/4/ec/apk/937663/content/SCREENSHOT/bd14f5eb-dbb0-4eb7-a1ab-2b3f7432ad28.png@webp",
                            "https://static.rustore.ru/imgproxy/Qbv53_EafnrvXOefOCBNDI-xnjjUY4cVBy0sCb4raOM/preset:web_scr_prt_162/plain/https://static.rustore.ru/701d5a12-510f-4c02-97c2-9c29a1bf1af1@webp",
                            "https://static.rustore.ru/imgproxy/UFsWIQFGY8vG3_wkqYkmTANG42se3jOmd2TjSBP0_NU/preset:web_scr_prt_162/plain/https://static.rustore.ru/87b0a8f6-a61a-4ba4-a777-a5bded5c166d@webp",
                            "https://static.rustore.ru/imgproxy/YxCdZcJeIX-UG5xWNqQPJ2K_0NCg8JLUXTMXEesXjmI/preset:web_scr_prt_384/plain/https://static.rustore.ru/2026/2/3/ff/apk/937663/content/SCREENSHOT/d00f5f2f-dcfd-4164-96f2-51146df67375.png@webp"
                        ),
                        description = "Прогноз погоды на сегодня и ближайшие дни с картой осадков."
                    ),
                    similarApps = List(15) { index ->
                        SimilarApp(
                            id = "similar-app-$index",
                            name = "Гильдия Героев: Экшен ММО РПГ: $index",
                            iconUrl = "https://static.rustore.ru/imgproxy/APsbtHxkVa4MZ0DXjnIkSwFQ_KVIcqHK9o3gHY6pvOQ/preset:web_app_icon_62/plain/https://static.rustore.ru/apk/393868735/content/ICON/3f605e3e-f5b3-434c-af4d-77bc5f38820e.png@webp",
                        )
                    },
                    descriptionCollapsed = true
                ),

                modifier = Modifier.fillMaxSize(),
                onBackClick = {},
                onShareClick = {},
                onInstallClick = {},
                onReadMoreClick = {},
                onDeveloperClick = {},
                onSimilarAppDownloadClick = {},
            )

             */

