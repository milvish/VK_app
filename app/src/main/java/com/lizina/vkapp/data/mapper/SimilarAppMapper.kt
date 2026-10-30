package com.lizina.vkapp.data.mapper

import com.lizina.vkapp.data.dto.SimilarAppDto
import com.lizina.vkapp.domain.appdetails.SimilarApp

fun SimilarAppDto.toDomain(): SimilarApp = SimilarApp(
    id = id,
    name = name,
    iconUrl = iconUrl,
)