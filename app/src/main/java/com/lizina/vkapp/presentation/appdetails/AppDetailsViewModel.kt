package com.lizina.vkapp.presentation.appdetails

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lizina.vkapp.domain.appdetails.GetAppDetailsUseCase
import com.lizina.vkapp.domain.appdetails.GetAppsUseCase
import com.lizina.vkapp.domain.appdetails.GetSimilarAppsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.Channel.Factory.BUFFERED
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.delay
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.coroutines.coroutineContext
import javax.inject.Inject
import kotlin.time.Duration.Companion.milliseconds

@HiltViewModel
class AppDetailsViewModel @Inject constructor(
    private val getAppDetails: GetAppDetailsUseCase,
    private val getSimilarApps: GetSimilarAppsUseCase,
    private val getApps: GetAppsUseCase,
) : ViewModel() {

    private val _events = Channel<AppDetailsEvent>(BUFFERED)
    val events = _events.receiveAsFlow()

    private val _state = MutableStateFlow<AppDetailsState>(AppDetailsState.Loading)
    val state = _state.asStateFlow()

    private var loadingJob: Job? = null
    private var installJob: Job? = null

    fun loadAppDetails(id: String) {
        Log.d("AppDetailsVM", "loadAppDetails, id='$id'")
        loadingJob?.cancel()
        loadingJob = viewModelScope.launch {
            _state.value = AppDetailsState.Loading
            try {
                val similar = getSimilarApps()
                val showcase = getApps().shuffled().take(9)

                getAppDetails(id).collect { appDetails ->
                    val currentState = _state.value
                    _state.value = AppDetailsState.Content(
                        appDetails = appDetails,
                        similarApps = similar,
                        showcaseApps = showcase,
                        descriptionCollapsed = (currentState as? AppDetailsState.Content)
                            ?.descriptionCollapsed ?: false,
                        installState = (currentState as? AppDetailsState.Content)
                            ?.installState ?: InstallState.NotInstalled,
                    )
                }
            } catch (e: CancellationException) {
                throw e
            } catch (e: Exception) {
                _state.value = AppDetailsState.Error
                Log.e("AppDetailsVM", "Failed to load app details for id='$id'", e)
            }
        }
    }

    fun onInstallClick() {
        val current = _state.value
        if (current !is AppDetailsState.Content) return

        when (current.installState) {
            is InstallState.Installing -> {
                updateInstallState(InstallState.NotInstalled)
                installJob?.cancel()
                installJob = null
            }

            is InstallState.NotInstalled -> {
                installJob = viewModelScope.launch {
                    try {
                        emulateInstall()
                    } catch (e: CancellationException) {
                        throw e
                    }
                }
            }

            is InstallState.Installed -> {
            }
        }
    }

    private suspend fun emulateInstall() {
        for (progress in 0..100 step 5) {
            delay(150.milliseconds)
            currentCoroutineContext().ensureActive()
            updateInstallState(InstallState.Installing(progress))
        }
        currentCoroutineContext().ensureActive()
        updateInstallState(InstallState.Installed)
        installJob = null
    }

    private fun updateInstallState(newState: InstallState) {
        _state.update { currentState ->
            if (currentState is AppDetailsState.Content) {
                currentState.copy(installState = newState)
            } else {
                currentState
            }
        }
    }

    fun showUnderDevelopmentMessage() {
        viewModelScope.launch {
            _events.send(AppDetailsEvent.UnderDevelopment)
        }
    }

    fun collapseDescription() {
        _state.update { currentState ->
            if (currentState is AppDetailsState.Content) {
                currentState.copy(descriptionCollapsed = true)
            } else {
                currentState
            }
        }
    }
}