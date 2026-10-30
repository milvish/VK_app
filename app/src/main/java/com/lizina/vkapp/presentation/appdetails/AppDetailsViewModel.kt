package com.lizina.vkapp.presentation.appdetails

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lizina.vkapp.domain.appdetails.GetAppDetailsUseCase
import com.lizina.vkapp.domain.appdetails.GetAppsUseCase
import com.lizina.vkapp.domain.appdetails.GetSimilarAppsUseCase
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.Channel.Factory.BUFFERED
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


class AppDetailsViewModel(
    initialUserId: String?,
    private val getAppDetails: GetAppDetailsUseCase,
    private val getSimilarApps: GetSimilarAppsUseCase,
    private val getApps: GetAppsUseCase
) : ViewModel() {


    private val _events = Channel<AppDetailsEvent>(BUFFERED)
    val events = _events.receiveAsFlow()

    private val _state = MutableStateFlow<AppDetailsState>(AppDetailsState.Loading)
    val state = _state.asStateFlow()

    private val _userId = MutableStateFlow<String>(initialUserId ?: "")
    val userId = _userId.asStateFlow()

    fun setUserId(newId: String) {
        _userId.value = newId
    }

    private var loadingJob: Job? = null

    init {
        viewModelScope.launch {
            _userId.collect { id ->
                if (id.isNotEmpty()) {
                    loadAppDetails()
                }
            }
        }
    }

    fun loadAppDetails() {
        loadingJob?.cancel()
        loadingJob = viewModelScope.launch {
            _state.value = AppDetailsState.Loading
            try {
                val similar = getSimilarApps()
                val showcase = getApps().shuffled().take(9)
                getAppDetails(userId.value).collect { appDetails ->
                    val currentState = _state.value
                    _state.value = AppDetailsState.Content(
                        appDetails = appDetails,
                        similarApps = similar,
                        showcaseApps = showcase,
                        descriptionCollapsed = (currentState as? AppDetailsState.Content)
                            ?.descriptionCollapsed ?: false,
                    )
                }
            } catch (e: CancellationException) {
                throw e
            } catch (e: Exception) {
                _state.value = AppDetailsState.Error
                Log.e("AppDetailsViewModel", "Failed to load app details", e)
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