package com.lizina.vkapp

import com.lizina.vkapp.domain.appdetails.AppDetails
import com.lizina.vkapp.domain.appdetails.AppDetailsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class HardcodedAppDetailsRepository(
    private val apps: List<AppDetails>
) : AppDetailsRepository {

    override suspend fun getAppDetails(id: String): Flow<AppDetails> = flow {
        val app = apps.find { it.id == id }
        if (app != null) {
            emit(app)
        } else {
            // Если не нашли — можно либо ничего не эмитить, либо кинуть ошибку.
            // Для ViewModel с collect удобнее не кидать ошибку здесь, а обработать в ViewModel.
        }
    }
}
