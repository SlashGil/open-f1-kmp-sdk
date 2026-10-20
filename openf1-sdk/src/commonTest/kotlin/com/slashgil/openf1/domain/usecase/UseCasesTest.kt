package com.slashgil.openf1.domain.usecase

import com.slashgil.openf1.data.repository.DefaultDriverRepository
import com.slashgil.openf1.data.repository.DefaultRaceEventRepository
import com.slashgil.openf1.data.repository.DefaultSessionRepository
import com.slashgil.openf1.data.repository.DefaultTelemetryRepository
import com.slashgil.openf1.domain.model.specifier.SessionSpecifier
import io.ktor.client.HttpClient
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.headersOf
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.json.Json
import com.slashgil.openf1.impl.DefaultOpenF1Client
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class UseCasesTest {

    private fun createTestClient(jsonResponse: String): DefaultOpenF1Client {
        val mockEngine = MockEngine {
            respond(
                content = jsonResponse,
                status = HttpStatusCode.OK,
                headers = headersOf(HttpHeaders.ContentType, "application/json")
            )
        }

        val httpClient = HttpClient(mockEngine) {
            install(ContentNegotiation) {
                json(Json {
                    ignoreUnknownKeys = true
                    isLenient = true
                })
            }
        }

        return DefaultOpenF1Client(
            baseUrl = "https://api.openf1.org/v1",
            httpClient = httpClient
        )
    }

    @Test
    fun testGetCarDataUseCase() = runTest {
        val json = """[{"driver_number": 1, "speed": 315}]"""
        val client = createTestClient(json)
        val repo = DefaultTelemetryRepository(client)
        val useCase = GetCarDataUseCase(repo)

        val result = useCase(driverNumber = 1)
        assertEquals(1, result.size)
        assertEquals(315, result.first().speed)
    }

    @Test
    fun testGetChampionshipDriversUseCase() = runTest {
        val json = """[{"driver_number": 1, "position_current": 1, "points_current": 400.0}]"""
        val client = createTestClient(json)
        val repo = DefaultDriverRepository(client)
        val useCase = GetChampionshipDriversUseCase(repo)

        val result = useCase()
        assertEquals(1, result.size)
        assertEquals(1, result.first().positionCurrent)
    }

    @Test
    fun testGetChampionshipTeamsUseCase() = runTest {
        val json = """[{"team_name": "Red Bull Racing", "position_current": 1}]"""
        val client = createTestClient(json)
        val repo = DefaultDriverRepository(client)
        val useCase = GetChampionshipTeamsUseCase(repo)

        val result = useCase()
        assertEquals(1, result.size)
        assertEquals("Red Bull Racing", result.first().teamName)
    }

    @Test
    fun testGetDriversUseCase() = runTest {
        val json = """[{"driver_number": 44, "full_name": "Lewis HAMILTON"}]"""
        val client = createTestClient(json)
        val repo = DefaultDriverRepository(client)
        val useCase = GetDriversUseCase(repo)

        val result = useCase(driverNumber = 44)
        assertEquals(1, result.size)
        assertEquals("Lewis HAMILTON", result.first().fullName)
    }

    @Test
    fun testGetIntervalsUseCase() = runTest {
        val json = """[{"driver_number": 1, "gap_to_leader": 0.0}]"""
        val client = createTestClient(json)
        val repo = DefaultRaceEventRepository(client)
        val useCase = GetIntervalsUseCase(repo)

        val result = useCase()
        assertEquals(1, result.size)
        assertEquals(0.0, result.first().gapToLeader)
    }

    @Test
    fun testGetLapsUseCase() = runTest {
        val json = """[{"driver_number": 1, "lap_number": 10, "lap_duration": 85.5}]"""
        val client = createTestClient(json)
        val repo = DefaultRaceEventRepository(client)
        val useCase = GetLapsUseCase(repo)

        val result = useCase()
        assertEquals(1, result.size)
        assertEquals(10, result.first().lapNumber)
    }

    @Test
    fun testGetLocationUseCase() = runTest {
        val json = """[{"driver_number": 1, "x": 100, "y": 200, "z": 10}]"""
        val client = createTestClient(json)
        val repo = DefaultTelemetryRepository(client)
        val useCase = GetLocationUseCase(repo)

        val result = useCase()
        assertEquals(1, result.size)
        assertEquals(100, result.first().x)
    }

    @Test
    fun testGetMeetingsUseCase() = runTest {
        val json = """[{"meeting_name": "Monaco Grand Prix", "year": 2024}]"""
        val client = createTestClient(json)
        val repo = DefaultSessionRepository(client)
        val useCase = GetMeetingsUseCase(repo)

        val result = useCase(year = 2024)
        assertEquals(1, result.size)
        assertEquals("Monaco Grand Prix", result.first().meetingName)
    }

    @Test
    fun testGetOvertakesUseCase() = runTest {
        val json = """[{"overtaking_driver_number": 4, "overtaken_driver_number": 16}]"""
        val client = createTestClient(json)
        val repo = DefaultRaceEventRepository(client)
        val useCase = GetOvertakesUseCase(repo)

        val result = useCase()
        assertEquals(1, result.size)
        assertEquals(4, result.first().overtakingDriverNumber)
    }

    @Test
    fun testGetPitUseCase() = runTest {
        val json = """[{"driver_number": 16, "stop_duration": 2.3}]"""
        val client = createTestClient(json)
        val repo = DefaultRaceEventRepository(client)
        val useCase = GetPitUseCase(repo)

        val result = useCase()
        assertEquals(1, result.size)
        assertEquals(2.3, result.first().stopDuration)
    }

    @Test
    fun testGetPitStopsUseCase() = runTest {
        val json = """[{"driver_number": 16, "stop_number": 1}]"""
        val client = createTestClient(json)
        val repo = DefaultRaceEventRepository(client)
        val useCase = GetPitStopsUseCase(repo)

        val result = useCase()
        assertEquals(1, result.size)
        assertEquals(1, result.first().stopNumber)
    }

    @Test
    fun testGetPositionUseCase() = runTest {
        val json = """[{"driver_number": 1, "position": 1}]"""
        val client = createTestClient(json)
        val repo = DefaultRaceEventRepository(client)
        val useCase = GetPositionUseCase(repo)

        val result = useCase()
        assertEquals(1, result.size)
        assertEquals(1, result.first().position)
    }

    @Test
    fun testGetRaceControlUseCase() = runTest {
        val json = """[{"category": "Flag", "flag": "GREEN", "message": "TRACK CLEAR"}]"""
        val client = createTestClient(json)
        val repo = DefaultRaceEventRepository(client)
        val useCase = GetRaceControlUseCase(repo)

        val result = useCase()
        assertEquals(1, result.size)
        assertEquals("TRACK CLEAR", result.first().message)
    }

    @Test
    fun testGetSessionsUseCase() = runTest {
        val json = """[{"session_name": "Race", "session_type": "Race", "year": 2024}]"""
        val client = createTestClient(json)
        val repo = DefaultSessionRepository(client)
        val useCase = GetSessionsUseCase(repo)

        val result = useCase(year = 2024)
        assertEquals(1, result.size)
        assertEquals("Race", result.first().sessionName)
    }

    @Test
    fun testGetStintsUseCase() = runTest {
        val json = """[{"driver_number": 81, "compound": "MEDIUM", "stint_number": 1}]"""
        val client = createTestClient(json)
        val repo = DefaultRaceEventRepository(client)
        val useCase = GetStintsUseCase(repo)

        val result = useCase()
        assertEquals(1, result.size)
        assertEquals(81, result.first().driverNumber)
    }

    @Test
    fun testGetTeamRadioUseCase() = runTest {
        val json = """[{"driver_number": 55, "recording_url": "https://api.openf1.org/v1/team_radio"}]"""
        val client = createTestClient(json)
        val repo = DefaultRaceEventRepository(client)
        val useCase = GetTeamRadioUseCase(repo)

        val result = useCase()
        assertEquals(1, result.size)
        assertEquals(55, result.first().driverNumber)
    }

    @Test
    fun testGetWeatherUseCase() = runTest {
        val json = """[{"air_temperature": 25.0, "track_temperature": 40.0}]"""
        val client = createTestClient(json)
        val repo = DefaultRaceEventRepository(client)
        val useCase = GetWeatherUseCase(repo)

        val result = useCase()
        assertEquals(1, result.size)
        assertEquals(25.0, result.first().airTemperature)
    }

    @Test
    fun testGetSessionLeaderboardUseCase() = runTest {
        val json = """[{"driver_number": 1, "full_name": "Max VERSTAPPEN", "session_key": 9158}]"""
        val client = createTestClient(json)
        val driverRepo = DefaultDriverRepository(client)
        val raceEventRepo = DefaultRaceEventRepository(client)
        val useCase = GetSessionLeaderboardUseCase(driverRepo, raceEventRepo)

        val leaderboard = useCase(sessionKey = SessionSpecifier.Key(9158))
        assertNotNull(leaderboard)
        assertEquals(1, leaderboard.entries.size)
        assertEquals("Max VERSTAPPEN", leaderboard.entries.first().driver?.fullName)
    }
}
