package com.lizina.vkapp.data.network.dto

import kotlinx.serialization.Serializable

@Serializable
data class AppDetailsNetworkDto(
    val id: String,
    val name: String,
    val description: String,
    val category: String,
    val iconUrl: String,

    // Этих полей нет в ответе сервера, добавляем дефолты, чтобы не было краша
    val developer: String = "",
    val ageRating: Int = 0,
    val size: Float = 0f,
    val screenshotUrlList: List<String>? = null
)