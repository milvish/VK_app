package com.lizina.vkapp.data.mapper

import com.lizina.vkapp.data.dto.AppDetailsDto
import com.lizina.vkapp.domain.appdetails.AppDetails
import com.lizina.vkapp.domain.appdetails.Category

fun AppDetailsDto.toDomain(): AppDetails = AppDetails(
    id = id,
    name = name,
    developer = developer,
    category = categoryFromString(category),
    ageRating = ageRating,
    size = size,
    iconUrl = iconUrl,
    screenshotUrlList = screenshotUrlList,
    description = description,
)

private fun categoryFromString(value: String): Category =
    Category.entries.firstOrNull { it.name == value } ?: Category.APP