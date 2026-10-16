package com.slashgil.openf1.model

import kotlinx.serialization.json.Json
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class DtoTest {

    private val json = Json {
        ignoreUnknownKeys = true
        isLenient = true
    }

    @Test
    fun testDriverDeserialization() {
        val sample = """
            {
                "broadcast_name": "L HAMILTON",
                "country_code": "GBR",
                "driver_number": 44,
                "first_name": "Lewis",
                "full_name": "Lewis HAMILTON",
                "headshot_url": "https://www.formula1.com/content/dam/fom-website/drivers/L/LEWHAM01_Lewis_Hamilton/lewham01.png",
                "last_name": "Hamilton",
                "meeting_key": 1219,
                "name_acronym": "HAM",
                "session_key": 9158,
                "team_colour": "6CD3BF",
                "team_name": "Mercedes"
            }
        """.trimIndent()

        val driver = json.decodeFromString<Driver>(sample)
        assertEquals("Lewis HAMILTON", driver.fullName)
        assertEquals(44, driver.driverNumber)
        assertEquals("Mercedes", driver.teamName)
        assertEquals("HAM", driver.nameAcronym)
    }

    @Test
    fun testLapDeserialization() {
        val sample = """
            {
                "date_start": "2023-09-15T13:05:00.000+00:00",
                "driver_number": 1,
                "duration_sector_1": 28.5,
                "duration_sector_2": 39.2,
                "duration_sector_3": 22.1,
                "i1_speed": 315,
                "i2_speed": 290,
                "is_pit_out_lap": false,
                "lap_duration": 89.8,
                "lap_number": 12,
                "meeting_key": 1219,
                "segments_sector_1": [2048, 2048],
                "segments_sector_2": [2048, 2048],
                "segments_sector_3": [2048, 2048],
                "session_key": 9158,
                "st_speed": 320
            }
        """.trimIndent()

        val lap = json.decodeFromString<Lap>(sample)
        assertEquals(1, lap.driverNumber)
        assertEquals(12, lap.lapNumber)
        assertEquals(89.8, lap.lapDuration)
        assertEquals(2, lap.segmentsSector1?.size)
    }

    @Test
    fun testPitStopDeserialization() {
        val sample = """
            {
                "date": "2023-09-15T13:30:00.000+00:00",
                "driver_number": 16,
                "lap_number": 20,
                "meeting_key": 1219,
                "pit_duration": 22.4,
                "session_key": 9158,
                "stop_number": 1
            }
        """.trimIndent()

        val pitStop = json.decodeFromString<PitStop>(sample)
        assertEquals(16, pitStop.driverNumber)
        assertEquals(20, pitStop.lapNumber)
        assertEquals(22.4, pitStop.pitDuration)
    }

    @Test
    fun testStintDeserialization() {
        val sample = """
            {
                "compound": "MEDIUM",
                "driver_number": 81,
                "lap_end": 15,
                "lap_start": 1,
                "meeting_key": 1219,
                "session_key": 9158,
                "stint_number": 1,
                "tyre_age_at_start": 0
            }
        """.trimIndent()

        val stint = json.decodeFromString<Stint>(sample)
        assertEquals("MEDIUM", stint.compound)
        assertEquals(81, stint.driverNumber)
        assertEquals(1, stint.stintNumber)
    }

    @Test
    fun testTeamRadioDeserialization() {
        val sample = """
            {
                "date": "2023-09-15T13:45:00.000+00:00",
                "driver_number": 55,
                "meeting_key": 1219,
                "recording_url": "https://api.openf1.org/v1/team_radio?session_key=9158&driver_number=55",
                "session_key": 9158
            }
        """.trimIndent()

        val radio = json.decodeFromString<TeamRadio>(sample)
        assertEquals(55, radio.driverNumber)
        assertNotNull(radio.recordingUrl)
    }
}
