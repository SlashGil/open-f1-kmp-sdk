package com.slashgil.openf1.data.mapper

import com.slashgil.openf1.domain.model.CarTelemetry
import com.slashgil.openf1.domain.model.DriverProfile
import com.slashgil.openf1.domain.model.LapTiming
import com.slashgil.openf1.domain.model.TrackWeather
import com.slashgil.openf1.domain.model.enums.RaceControlCategory
import com.slashgil.openf1.domain.model.enums.RaceControlFlag
import com.slashgil.openf1.domain.model.enums.SessionType
import com.slashgil.openf1.domain.model.enums.TyreCompound
import com.slashgil.openf1.domain.result.Result
import com.slashgil.openf1.domain.result.asResult
import com.slashgil.openf1.model.CarData
import com.slashgil.openf1.model.Driver
import com.slashgil.openf1.model.Lap
import com.slashgil.openf1.model.Weather
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

class OpenF1MappersTest {

    @Test
    fun testCarDataToDomainFactory() {
        val dto = CarData(
            driverNumber = 44,
            speed = 320,
            rpm = 11500,
            drs = 12,
            date = "2023-09-15T13:05:00.000+00:00"
        )
        val domain = CarTelemetry.from(dto)

        assertEquals(44, domain.driverNumber)
        assertEquals(320, domain.speed)
        assertTrue(domain.isDrsActive)
        assertNotNull(domain.date)
    }

    @Test
    fun testDriverToDomainFactory() {
        val dto = Driver(
            driverNumber = 1,
            fullName = "Max VERSTAPPEN",
            teamName = "Red Bull Racing"
        )
        val domain = DriverProfile.from(dto)

        assertEquals(1, domain.driverNumber)
        assertEquals("Max VERSTAPPEN", domain.fullName)
        assertEquals("Red Bull Racing", domain.teamName)
    }

    @Test
    fun testLapToDomainFactoryAndFormattedDuration() {
        val dto = Lap(
            driverNumber = 16,
            lapNumber = 5,
            lapDuration = 89.845,
            dateStart = "2023-09-15T13:10:00.000Z"
        )
        val domain = LapTiming.from(dto)

        assertEquals(16, domain.driverNumber)
        assertEquals("1:29.845", domain.formattedLapDuration)
        assertNotNull(domain.dateStart)
    }

    @Test
    fun testEnumParsing() {
        assertEquals(TyreCompound.SOFT, TyreCompound.from("SOFT"))
        assertEquals(TyreCompound.MEDIUM, TyreCompound.from("medium"))
        assertEquals(TyreCompound.UNKNOWN, TyreCompound.from("UNKNOWN_COMPOUND"))

        assertEquals(SessionType.RACE, SessionType.from("Race"))
        assertEquals(SessionType.PRACTICE, SessionType.from("Practice 1"))
        assertEquals(SessionType.QUALIFYING, SessionType.from("Qualifying"))

        assertEquals(RaceControlFlag.YELLOW, RaceControlFlag.from("YELLOW"))
        assertEquals(RaceControlCategory.FLAG, RaceControlCategory.from("Flag"))
        assertEquals(RaceControlCategory.SAFETY_CAR, RaceControlCategory.from("SafetyCar"))
    }

    @Test
    fun testWeatherToDomainFactory() {
        val dto = Weather(
            airTemperature = 22.5,
            trackTemperature = 35.0,
            rainfall = 1
        )
        val domain = TrackWeather.from(dto)

        assertEquals(22.5, domain.airTemperature)
        assertEquals(35.0, domain.trackTemperature)
        assertTrue(domain.isRaining == true)
    }

    @Test
    fun testResultClass() = runTest {
        val success: Result<String> = Result.Success("Data")
        assertTrue(success.isSuccess)
        assertEquals("Data", success.getOrNull())

        val error: Result<String> = Result.Error(IllegalStateException("Network failure"))
        assertTrue(error.isError)
        assertNotNull(error.exceptionOrNull())

        val loading: Result<String> = Result.Loading
        assertTrue(loading.isLoading)

        val wrappedResult = asResult { "Hello F1" }
        assertTrue(wrappedResult is Result.Success)
        assertEquals("Hello F1", wrappedResult.data)
    }
}
