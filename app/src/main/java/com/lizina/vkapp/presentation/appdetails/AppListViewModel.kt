package com.lizina.vkapp.presentation.appdetails

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lizina.vkapp.domain.appdetails.AppDetails
import com.lizina.vkapp.domain.appdetails.GetAppsUseCase
import com.lizina.vkapp.domain.appdetails.GetRatingsInParallelUseCase
import com.lizina.vkapp.domain.appdetails.GetRatingsSequentiallyUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.system.measureTimeMillis

@HiltViewModel
class AppListViewModel @Inject constructor(
    private val getApps: GetAppsUseCase,
    private val getRatingsSequentially: GetRatingsSequentiallyUseCase,
    private val getRatingsInParallel: GetRatingsInParallelUseCase,
) : ViewModel() {

    private val _apps = MutableStateFlow<List<AppDetails>>(emptyList())
    val apps = _apps.asStateFlow()

    private val _ratings = MutableStateFlow<Map<String, Float>>(emptyMap())
    val ratings = _ratings.asStateFlow()

    private val _snackbarMessage = MutableStateFlow<String?>(null)
    val snackbarMessage = _snackbarMessage.asStateFlow()

    init {
        viewModelScope.launch {
            _apps.value = getApps()
            Log.d("RatingsBench", "Загружено приложений: ${_apps.value.size}")
        }
    }

    fun loadRatingsSequentially() {
        viewModelScope.launch {
            val ids = _apps.value.map { it.id }
            if (ids.isEmpty()) return@launch
            val elapsed = measureTimeMillis {
                _ratings.value = getRatingsSequentially(ids)
            }
            Log.d("RatingsBench", "SEQUENTIAL: ${elapsed} ms for ${ids.size} apps")
            _snackbarMessage.value = "Последовательно: ${elapsed} мс"
        }
    }

    fun loadRatingsInParallel() {
        viewModelScope.launch {
            val ids = _apps.value.map { it.id }
            if (ids.isEmpty()) return@launch
            val elapsed = measureTimeMillis {
                _ratings.value = getRatingsInParallel(ids)
            }
            Log.d("RatingsBench", "PARALLEL: ${elapsed} ms for ${ids.size} apps")
            _snackbarMessage.value = "Параллельно: ${elapsed} мс"
        }
    }

    fun onLogoClicked() { _snackbarMessage.value = "Логотип нажат!" }
    fun clearSnackbar() { _snackbarMessage.value = null }
}