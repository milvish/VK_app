package com.lizina.vkapp.data.network

import com.lizina.vkapp.data.network.dto.AppDetailsNetworkDto
import com.lizina.vkapp.data.network.dto.CatalogItemDto
import kotlinx.coroutines.delay
import retrofit2.http.GET
import retrofit2.http.Path
import kotlin.time.Duration.Companion.milliseconds

interface VkAppApi {

    @GET("catalog")
    suspend fun getCatalog(): List<CatalogItemDto>

    @GET("{id}")
    suspend fun getAppDetails(@Path("id") id: String): AppDetailsNetworkDto

    suspend fun getRating(id: String): Float {
        delay(1500L.milliseconds)
        return (30..50).random() / 10f
    }
}