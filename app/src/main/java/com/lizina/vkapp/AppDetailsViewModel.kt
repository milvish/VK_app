package com.lizina.vkapp

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lizina.vkapp.domain.appdetails.AppDetails
import com.lizina.vkapp.domain.appdetails.AppDetailsRepository
import com.lizina.vkapp.domain.appdetails.GetAppDetailsUseCase
import com.lizina.vkapp.presentation.appdetails.AppDetailsEvent
import com.lizina.vkapp.presentation.appdetails.AppDetailsState
import com.lizina.vkapp.presentation.appdetails.hardcodedApps
import com.lizina.vkapp.presentation.appdetails.similarApps
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.Channel.Factory.BUFFERED
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


class AppDetailsViewModel(
    initialUserId: String?
): ViewModel() {

    val appDetailsRepository = object : AppDetailsRepository {
        override suspend fun getAppDetails(id: String): Flow<AppDetails> = flow {
            hardcodedApps.find { it.id == id }?.let { emit(it) }
        }
    }

    val getAppDetailsUseCase = GetAppDetailsUseCase(appDetailsRepository)

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
                    getAppDetails()
                }
            }
        }
    }

    fun getAppDetails() {
        loadingJob?.cancel()
        loadingJob = viewModelScope.launch {
            _state.value = AppDetailsState.Loading

            try {
                val similarApps = similarApps
                getAppDetailsUseCase(userId.value).collect { appDetails ->
                    val currentState = _state.value
                    _state.value = AppDetailsState.Content(
                        appDetails = appDetails,
                        similarApps = similarApps,
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