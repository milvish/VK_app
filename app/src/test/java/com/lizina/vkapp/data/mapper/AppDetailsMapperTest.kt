package com.lizina.vkapp.data.mapper

import com.lizina.vkapp.data.dto.AppDetailsDto
import com.lizina.vkapp.domain.appdetails.Category
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test

class AppDetailsMapperTest {

    private fun buildDto(
        category: String = "PRODUCTIVITY",
        screenshots: List<String>? = listOf("a", "b"),
    ) = AppDetailsDto(
        id = "id-1",
        name = "Календарь",
        developer = "Team",
        category = category,
        ageRating = 3,
        size = 12.5f,
        iconUrl = "icon",
        screenshotUrlList = screenshots,
        description = "desc",
    )

    @Test
    fun `toDomain maps all scalar fields`() {
        val dto = buildDto()
        val domain = dto.toDomain()

        assertEquals("id-1", domain.id)
        assertEquals("Календарь", domain.name)
        assertEquals("Team", domain.developer)
        assertEquals(3, domain.ageRating)
        assertEquals(12.5f, domain.size, 0.001f)
        assertEquals("icon", domain.iconUrl)
        assertEquals("desc", domain.description)
    }

    @Test
    fun `toDomain maps valid enum name to Category`() {
        assertEquals(
            Category.PRODUCTIVITY,
            buildDto(category = "PRODUCTIVITY").toDomain().category,
        )
    }

    @Test
    fun `toDomain falls back to APP for unknown category`() {
        assertEquals(Category.APP, buildDto(category = "UNKNOWN_CAT").toDomain().category)
    }

    @Test
    fun `toDomain maps null screenshots as null`() {
        assertNull(buildDto(screenshots = null).toDomain().screenshotUrlList)
    }

    @Test
    fun `toDomain maps empty screenshots as empty list`() {
        assertEquals(
            emptyList<String>(),
            buildDto(screenshots = emptyList()).toDomain().screenshotUrlList,
        )
    }
}