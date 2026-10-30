package com.lizina.vkapp.domain.appdetails

import androidx.compose.runtime.Composable
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

enum class Category {
    APP,

    GAME,


    PRODUCTIVITY,

    SOCIAL,

    EDUCATION,

    ENTERTAINMENT,

    MUSIC,


    VIDEO,


    PHOTOGRAPHY,


    HEALTH,


    SPORTS,


    NEWS,


    BOOKS,


    BUSINESS,


    FINANCE,


    TRAVEL,


    MAPS,


    FOOD,


    SHOPPING,


    UTILITIES
}

/*
@Serializable
enum class Category {
    @SerialName("Приложения")
    APP,

    @SerialName("Игры")
    GAME,

    @SerialName("Производительность")
    PRODUCTIVITY,

    @SerialName("Социальные сети")
    SOCIAL,

    @SerialName("Образование")
    EDUCATION,

    @SerialName("Развлечения")
    ENTERTAINMENT,

    @SerialName("Музыка")
    MUSIC,

    @SerialName("Видео")
    VIDEO,

    @SerialName("Фотография")
    PHOTOGRAPHY,

    @SerialName("Здоровье")
    HEALTH,

    @SerialName("Спорт")
    SPORTS,

    @SerialName("Новости")
    NEWS,

    @SerialName("Книги")
    BOOKS,

    @SerialName("Бизнес")
    BUSINESS,

    @SerialName("Финансы")
    FINANCE,

    @SerialName("Путешествия")
    TRAVEL,

    @SerialName("Карты")
    MAPS,

    @SerialName("Еда")
    FOOD,

    @SerialName("Покупки")
    SHOPPING,

    @SerialName("Утилиты")
    UTILITIES
}
 */