package com.lizina.vkapp

import androidx.lifecycle.ViewModel
import com.lizina.vkapp.domain.appdetails.AppDetails
import com.lizina.vkapp.presentation.appdetails.hardcodedApps
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class AppListViewModel : ViewModel() {
    private val _apps = MutableStateFlow(hardcodedApps)
    val apps: StateFlow<List<AppDetails>> = _apps.asStateFlow()

    private val _snackbarMessage = MutableStateFlow<String?>(null)
    val snackbarMessage: StateFlow<String?> = _snackbarMessage.asStateFlow()

    fun onLogoClicked() {
        _snackbarMessage.value = "Логотип нажат!"
    }

    fun clearSnackbar() {
        _snackbarMessage.value = null
    }
}
