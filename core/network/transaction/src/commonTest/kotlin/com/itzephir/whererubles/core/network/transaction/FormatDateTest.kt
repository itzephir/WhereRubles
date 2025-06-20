package com.itzephir.whererubles.core.network.transaction

import kotlinx.datetime.Instant
import kotlin.test.Test
import kotlin.test.assertEquals

class FormatDateTest {

    @Test
    fun testFormat() {
        val given = Instant.parse("2025-11-20T20:36:22.889Z")

        val expected = "2025-11-20"
        val actual = given.format()

        assertEquals(expected, actual)
    }

    @Test
    fun testFormatWithZeroMonth() {
        val given = Instant.parse("2025-06-20T20:36:22.889Z")

        val expected = "2025-06-20"
        val actual = given.format()

        assertEquals(expected, actual)
    }

    @Test
    fun testFormatWithZeroDay() {
        val given = Instant.parse("2025-06-02T20:36:22.889Z")

        val expected = "2025-06-02"
        val actual = given.format()

        assertEquals(expected, actual)
    }
}