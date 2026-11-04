package com.lizina.vkapp.data.repository

import com.lizina.vkapp.data.mapper.toDomain
import com.lizina.vkapp.data.network.VkAppApi
import com.lizina.vkapp.data.source.HardcodedAppsDataSource
import com.lizina.vkapp.domain.appdetails.AppDetails
import com.lizina.vkapp.domain.appdetails.AppDetailsRepository
import com.lizina.vkapp.domain.appdetails.SimilarApp
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AppDetailsRepositoryImpl @Inject constructor(
    private val api: VkAppApi,
    private val source: HardcodedAppsDataSource
) : AppDetailsRepository {

    /*
    // Выдаёт ошибку 404
    override suspend fun getAppDetails(id: String): Flow<AppDetails> = flow {
        emit(api.getAppDetails(id).toDomain())
    }
     */

    //С обработкой ошибки 404
    override suspend fun getAppDetails(id: String): Flow<AppDetails> = flow {
        // 1. Пытаемся получить детали с сервера
        val serverDetails = runCatching { api.getAppDetails(id).toDomain() }.getOrNull()

        if (serverDetails != null) {
            emit(serverDetails)
            return@flow
        }

        // 2. Если сервер вернул 404, пытаемся найти это приложение в каталоге
        val catalogItem = runCatching {
            api.getCatalog().firstOrNull { it.id == id }?.toDomain()
        }.getOrNull()

        if (catalogItem != null) {
            emit(catalogItem)
            return@flow
        }

        // 3. Если ничего не нашли — показываем сообщение об ошибке
        throw NoSuchElementException("Приложение с id=$id не найдено")
    }

    override suspend fun getSimilarApps(): List<SimilarApp> =
        source.similarApps.map { it.toDomain() }

    override suspend fun getApps(): List<AppDetails> =
        api.getCatalog().map { it.toDomain() }
        //source.apps.map { it.toDomain() }
}