package com.lizina.vkapp.data.network

import com.lizina.vkapp.data.network.dto.AppDetailsNetworkDto
import com.lizina.vkapp.data.network.dto.CatalogItemDto
import retrofit2.http.GET
import retrofit2.http.Path

interface VkAppApi {

    @GET("catalog")
    suspend fun getCatalog(): List<CatalogItemDto>

    @GET("{id}")
    suspend fun getAppDetails(@Path("id") id: String): AppDetailsNetworkDto
}