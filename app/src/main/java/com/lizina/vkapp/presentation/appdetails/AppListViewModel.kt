package com.lizina.vkapp.presentation.appdetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lizina.vkapp.domain.appdetails.AppDetails
import com.lizina.vkapp.domain.appdetails.GetAppsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AppListViewModel (
    private val getApps: GetAppsUseCase,
): ViewModel() {
    private val _apps = MutableStateFlow<List<AppDetails>>(emptyList())
    val apps = _apps.asStateFlow()
    init {
        viewModelScope.launch { _apps.value = getApps() }
    }
    private val _snackbarMessage = MutableStateFlow<String?>(null)
    val snackbarMessage: StateFlow<String?> = _snackbarMessage.asStateFlow()

    fun onLogoClicked() {
        _snackbarMessage.value = "Логотип нажат!"
    }

    fun clearSnackbar() {
        _snackbarMessage.value = null
    }
}
