package com.lizina.vkapp.presentation.appdetails

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.lizina.vkapp.Destination
import com.lizina.vkapp.domain.appdetails.AppDetails
import com.lizina.vkapp.ui.theme.VKAppTheme


@Composable
fun ShopWindowBlock(navController: NavController, modifier: Modifier) {
    val showcaseApps = hardcodedApps.shuffled().take(9)

    fun getShowcasePages(): List<List<AppDetails>> {
        return showcaseApps.chunked(3) // [0..2], [3..5], [6..8]
    }


    val pages = getShowcasePages()

    val pagerState = rememberPagerState(initialPage = 0){ pages.size }

    Column(modifier = modifier) {
        Text(text = "Витрина")
        Spacer(modifier = Modifier.height(8.dp))

        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxWidth(),
            pageSpacing = 8.dp,
            contentPadding = PaddingValues(horizontal = 4.dp),
        ) { pageIndex ->
            val appsInPage = pages[pageIndex]
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(vertical = 8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                appsInPage.forEach { app ->
                    AppListItem(
                        app = app,
                        navController = navController,
                        route = Destination.AppListDestination,
                    )
                }
            }
        }

    }
}

@Preview
@Composable
private fun ShopWindowBlockPreview() {
    VKAppTheme() {
        //ShopWindowBlock()
    }
}