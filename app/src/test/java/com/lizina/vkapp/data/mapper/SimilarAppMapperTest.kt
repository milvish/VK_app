package com.lizina.vkapp.data.mapper

import com.lizina.vkapp.data.dto.SimilarAppDto
import org.junit.Assert.assertEquals
import org.junit.Test

class SimilarAppMapperTest {

    private fun dto(id: String = "s1", name: String = "App", icon: String = "url") =
        SimilarAppDto(id = id, name = name, iconUrl = icon)

    @Test
    fun `toDomain maps id`() = assertEquals("s1", dto().toDomain().id)

    @Test
    fun `toDomain maps name`() = assertEquals("App", dto().toDomain().name)

    @Test
    fun `toDomain maps iconUrl`() = assertEquals("url", dto().toDomain().iconUrl)

    @Test
    fun `toDomain preserves empty name`() = assertEquals("", dto(name = "").toDomain().name)

    @Test
    fun `toDomain preserves empty icon`() = assertEquals("", dto(icon = "").toDomain().iconUrl)
}