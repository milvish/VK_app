package com.lizina.vkapp.data.mapper

import com.lizina.vkapp.data.network.dto.AppDetailsNetworkDto
import com.lizina.vkapp.data.network.dto.CatalogItemDto
import com.lizina.vkapp.domain.appdetails.Category
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test

class NetworkMapperTest {

    // ---------- CatalogItemDto → AppDetails ----------

    @Test
    fun `catalogItem maps id and name`() {
        val d = CatalogItemDto("id", "Name", "desc", "Игры", "icon").toDomain()
        assertEquals("id", d.id)
        assertEquals("Name", d.name)
    }

    @Test
    fun `catalogItem leaves developer empty`() {
        assertEquals("", CatalogItemDto("i", "n", "d", "Игры", "u").toDomain().developer)
    }

    @Test
    fun `catalogItem leaves ageRating and size zero`() {
        val d = CatalogItemDto("i", "n", "d", "Игры", "u").toDomain()
        assertEquals(0, d.ageRating)
        assertEquals(0f, d.size, 0f)
    }

    @Test
    fun `catalogItem maps russian category name`() {
        assertEquals(
            Category.GAME,
            CatalogItemDto("i", "n", "d", "Игры", "u").toDomain().category,
        )
    }

    @Test
    fun `catalogItem has null screenshots`() {
        assertNull(CatalogItemDto("i", "n", "d", "Игры", "u").toDomain().screenshotUrlList)
    }

    // ---------- AppDetailsNetworkDto → AppDetails ----------

    private fun netDto(
        developer: String = "Team",
        category: String = "MUSIC",
    ) = AppDetailsNetworkDto(
        id = "n1", name = "N", description = "desc", category = category,
        iconUrl = "icon", developer = developer, ageRating = 12, size = 33.3f,
        screenshotUrlList = listOf("s"),
    )

    @Test
    fun `network maps scalar fields`() {
        val d = netDto().toDomain()
        assertEquals("n1", d.id)
        assertEquals(12, d.ageRating)
        assertEquals(33.3f, d.size, 0.001f)
        assertEquals(listOf("s"), d.screenshotUrlList)
    }

    @Test
    fun `network empty developer replaced with placeholder`() {
        assertEquals("Неизвестен", netDto(developer = "").toDomain().developer)
    }

    @Test
    fun `network non-empty developer preserved`() {
        assertEquals("Team", netDto(developer = "Team").toDomain().developer)
    }

    @Test
    fun `network maps russian category aliases`() {
        assertEquals(
            Category.SOCIAL,
            netDto(category = "Общение").toDomain().category,
        )
    }

    @Test
    fun `network maps enum-like category case-insensitively`() {
        assertEquals(
            Category.MUSIC,
            netDto(category = "music").toDomain().category,
        )
    }

    @Test
    fun `network unknown category falls back to APP`() {
        assertEquals(Category.APP, netDto(category = "Нечто").toDomain().category)
    }
}