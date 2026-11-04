package com.lizina.vkapp.data.mapper

import com.lizina.vkapp.data.network.dto.AppDetailsNetworkDto
import com.lizina.vkapp.data.network.dto.CatalogItemDto
import com.lizina.vkapp.domain.appdetails.AppDetails
import com.lizina.vkapp.domain.appdetails.Category

fun CatalogItemDto.toDomain(): AppDetails = AppDetails(
    id = id,
    name = name,
    developer = "",                   // сервер /catalog не отдаёт
    category = categoryFromString(category),
    ageRating = 0,                    // сервер /catalog не отдаёт
    size = 0f,                        // сервер /catalog не отдаёт
    iconUrl = iconUrl,
    screenshotUrlList = null,         // сервер /catalog не отдаёт
    description = description,
)

fun AppDetailsNetworkDto.toDomain(): AppDetails = AppDetails(
    id = id,
    name = name,
    developer = developer.ifEmpty { "Неизвестен" }, // Красивая заглушка
    category = categoryFromString(category),
    ageRating = ageRating,
    size = size,
    iconUrl = iconUrl,
    screenshotUrlList = screenshotUrlList,
    description = description,
)

private fun categoryFromString(value: String): Category {
    Category.entries.firstOrNull { it.name.equals(value, ignoreCase = true) }
        ?.let { return it }

    return when (value) {
        "Приложения"                   -> Category.APP
        "Игры"                         -> Category.GAME
        "Производительность"           -> Category.PRODUCTIVITY
        "Социальные сети", "Общение"   -> Category.SOCIAL
        "Образование"                  -> Category.EDUCATION
        "Развлечения"                  -> Category.ENTERTAINMENT
        "Музыка"                       -> Category.MUSIC
        "Видео", "Фото и видео"        -> Category.VIDEO
        "Фотография"                   -> Category.PHOTOGRAPHY
        "Здоровье", "Здоровье и фитнес" -> Category.HEALTH
        "Спорт"                        -> Category.SPORTS
        "Новости"                      -> Category.NEWS
        "Книги", "Книги и справочники" -> Category.BOOKS
        "Бизнес"                       -> Category.BUSINESS
        "Финансы"                      -> Category.FINANCE
        "Путешествия"                  -> Category.TRAVEL
        "Карты", "Навигация"           -> Category.MAPS
        "Еда", "Еда и напитки"         -> Category.FOOD
        "Покупки", "Шопинг"            -> Category.SHOPPING
        "Утилиты"                      -> Category.UTILITIES
        "Образ жизни"                  -> Category.APP
        "Погода"                       -> Category.APP
        else                           -> Category.APP
    }
}