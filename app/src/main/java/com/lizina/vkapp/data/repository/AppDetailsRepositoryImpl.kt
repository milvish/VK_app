package com.lizina.vkapp.data.repository

import com.lizina.vkapp.data.mapper.toDomain
import com.lizina.vkapp.data.source.HardcodedAppsDataSource
import com.lizina.vkapp.domain.appdetails.AppDetails
import com.lizina.vkapp.domain.appdetails.AppDetailsRepository
import com.lizina.vkapp.domain.appdetails.SimilarApp
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class AppDetailsRepositoryImpl(
    private val source: HardcodedAppsDataSource
) : AppDetailsRepository {

    override suspend fun getAppDetails(id: String): Flow<AppDetails> = flow {
        source.apps.firstOrNull { it.id == id }?.let { dto ->
            emit(dto.toDomain())
        } ?: throw NoSuchElementException("App with id=$id not found")
    }

    override suspend fun getSimilarApps(): List<SimilarApp> =
        source.similarApps.map { it.toDomain() }

    override suspend fun getApps(): List<AppDetails> =
        source.apps.map { it.toDomain() }
}