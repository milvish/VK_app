package com.lizina.vkapp.presentation.appdetails

import androidx.compose.runtime.Immutable
import com.lizina.vkapp.domain.appdetails.AppDetails
import com.lizina.vkapp.domain.appdetails.SimilarApp


@Immutable
sealed interface AppDetailsState {
    data object Error : AppDetailsState
    data object Loading : AppDetailsState
    data class Content(
        val appDetails: AppDetails,
        val similarApps: List<SimilarApp>,
        val showcaseApps: List<AppDetails>,
        val descriptionCollapsed: Boolean,
    ) : AppDetailsState
}