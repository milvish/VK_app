package com.lizina.vkapp.domain.appdetails

import kotlinx.coroutines.flow.Flow


interface AppDetailsRepository {
    suspend fun getAppDetails(id: String): Flow<AppDetails>
    suspend fun getSimilarApps(): List<SimilarApp>

    suspend fun getApps(): List<AppDetails>
}