package com.slashgil.openf1.domain.model

import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

class TimestampFixtureTest {

    @Test
    fun testBahrainGrandPrixTimestampFixture() {
        // Real OpenF1 session start ISO string from 2024 Bahrain GP Race
        val isoString = "2024-03-02T15:00:00.000+00:00"
        val instant = isoString.toInstantOrNull()

        assertEquals(1709391600000L, instant?.toEpochMilliseconds())

        // Display in local race timezone (Sakhir / Bahrain = UTC+3)
        val bahrainTz = TimeZone.of("Asia/Bahrain")
        val bahrainTime = instant?.toLocalDateTime(bahrainTz)

        assertEquals(2024, bahrainTime?.year)
        assertEquals(3, bahrainTime?.monthNumber)
        assertEquals(2, bahrainTime?.dayOfMonth)
        assertEquals(18, bahrainTime?.hour) // 15:00 UTC = 18:00 AST
        assertEquals(0, bahrainTime?.minute)

        // Timezone conversion should NEVER mutate the underlying Instant
        assertEquals(1709391600000L, instant?.toEpochMilliseconds())
    }

    @Test
    fun testMonacoGrandPrixTimestampFixture() {
        // Real OpenF1 session start ISO string from 2024 Monaco GP Race
        val isoString = "2024-05-26T13:00:00.000Z"
        val instant = isoString.toInstantOrNull()

        assertEquals(1716728400000L, instant?.toEpochMilliseconds())

        // Display in Monaco local timezone (Europe/Monaco = CEST UTC+2 in May)
        val monacoTz = TimeZone.of("Europe/Monaco")
        val monacoTime = instant?.toLocalDateTime(monacoTz)

        assertEquals(15, monacoTime?.hour) // 13:00 UTC = 15:00 CEST

        // Display in Tokyo (Asia/Tokyo = JST UTC+9)
        val tokyoTz = TimeZone.of("Asia/Tokyo")
        val tokyoTime = instant?.toLocalDateTime(tokyoTz)

        assertEquals(22, tokyoTime?.hour) // 13:00 UTC = 22:00 JST

        // Ensure underlying Instant remains invariant
        assertEquals(1716728400000L, instant?.toEpochMilliseconds())
    }

    @Test
    fun testJapaneseGrandPrixTimestampFixture() {
        // Real OpenF1 session start ISO string from 2024 Japanese GP (Suzuka)
        val isoString = "2024-04-07T05:00:00.000+00:00"
        val instant = isoString.toInstantOrNull()

        assertEquals(1712466000000L, instant?.toEpochMilliseconds())

        val suzukaTz = TimeZone.of("Asia/Tokyo")
        val suzukaTime = instant?.toLocalDateTime(suzukaTz)

        assertEquals(14, suzukaTime?.hour) // 05:00 UTC = 14:00 JST
    }

    @Test
    fun testLasVegasGrandPrixTimestampFixture() {
        // Real OpenF1 session start ISO string from 2024 Las Vegas GP
        val isoString = "2024-11-24T06:00:00.000Z"
        val instant = isoString.toInstantOrNull()

        assertEquals(1732428000000L, instant?.toEpochMilliseconds())

        val vegasTz = TimeZone.of("America/Los_Angeles")
        val vegasTime = instant?.toLocalDateTime(vegasTz)

        assertEquals(23, vegasTime?.dayOfMonth) // Nov 23 local
        assertEquals(22, vegasTime?.hour) // Nov 23, 22:00 PST (UTC-8)
    }

    @Test
    fun testEdgeAndMalformedDateStrings() {
        assertNull(null.toInstantOrNull())
        assertNull("".toInstantOrNull())
        assertNull("   ".toInstantOrNull())
        assertNull("INVALID_DATE_STRING".toInstantOrNull())
    }
}
