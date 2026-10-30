package com.lizina.vkapp.presentation.appdetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.lizina.vkapp.data.repository.AppDetailsRepositoryImpl
import com.lizina.vkapp.data.source.HardcodedAppsDataSource
import com.lizina.vkapp.domain.appdetails.GetAppsUseCase

object AppListViewModelFactory : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val repository = AppDetailsRepositoryImpl(HardcodedAppsDataSource())
        return AppListViewModel(GetAppsUseCase(repository)) as T
    }
}