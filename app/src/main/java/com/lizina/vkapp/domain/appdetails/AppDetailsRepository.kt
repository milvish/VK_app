package com.lizina.vkapp.domain.appdetails

import kotlinx.coroutines.flow.Flow


interface AppDetailsRepository {
    suspend fun getAppDetails(id: String): Flow<AppDetails>
    suspend fun getSimilarApps(): List<SimilarApp>

    suspend fun getApps(): List<AppDetails>

    suspend fun loadRatingsSequentially(ids: List<String>): Map<String, Float>
    suspend fun loadRatingsInParallel(ids: List<String>): Map<String, Float>
}