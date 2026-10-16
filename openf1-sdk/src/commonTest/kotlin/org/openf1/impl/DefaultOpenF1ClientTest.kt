package org.openf1.impl

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
import org.openf1.contract.OpenF1Client
import org.openf1.exception.OpenF1Exception
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertTrue

class DefaultOpenF1ClientTest {

    private fun createMockClient(
        jsonResponse: String,
        status: HttpStatusCode = HttpStatusCode.OK,
        requestValidator: ((url: String) -> Unit)? = null
    ): OpenF1Client {
        val mockEngine = MockEngine { request ->
            requestValidator?.invoke(request.url.toString())
            respond(
                content = jsonResponse,
                status = status,
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
    fun testGetCarData() = runTest {
        val mockJson = """
            [
                {
                    "brake": 0,
                    "date": "2023-09-15T13:05:00.000+00:00",
                    "driver_number": 55,
                    "drs": 8,
                    "gear": 7,
                    "meeting_key": 1219,
                    "n_gear": 7,
                    "rpm": 11500,
                    "session_key": 9158,
                    "speed": 315,
                    "throttle": 100
                }
            ]
        """.trimIndent()

        val client = createMockClient(mockJson)
        val carData = client.getCarData(driverNumber = 55, sessionKey = 9158)
        assertEquals(1, carData.size)
        assertEquals(315, carData.first().speed)
        assertEquals(11500, carData.first().rpm)
    }

    @Test
    fun testGetChampionshipDrivers() = runTest {
        val mockJson = """
            [
                {
                    "driver_number": 4,
                    "meeting_key": 1219,
                    "points_current": 180.0,
                    "points_start": 165.0,
                    "position_current": 2,
                    "position_start": 3,
                    "session_key": 9839
                }
            ]
        """.trimIndent()

        val client = createMockClient(mockJson)
        val standings = client.getChampionshipDrivers(driverNumber = 4, sessionKey = "latest")
        assertEquals(1, standings.size)
        assertEquals(180.0, standings.first().pointsCurrent)
        assertEquals(2, standings.first().positionCurrent)
    }

    @Test
    fun testGetChampionshipTeams() = runTest {
        val mockJson = """
            [
                {
                    "meeting_key": 1219,
                    "points_current": 350.0,
                    "points_start": 330.0,
                    "position_current": 1,
                    "position_start": 1,
                    "session_key": 9839,
                    "team_name": "McLaren"
                }
            ]
        """.trimIndent()

        val client = createMockClient(mockJson)
        val teams = client.getChampionshipTeams(teamName = "McLaren", sessionKey = 9839)
        assertEquals(1, teams.size)
        assertEquals("McLaren", teams.first().teamName)
        assertEquals(350.0, teams.first().pointsCurrent)
    }

    @Test
    fun testGetDrivers() = runTest {
        val mockJson = """
            [
                {
                    "broadcast_name": "L HAMILTON",
                    "driver_number": 44,
                    "full_name": "Lewis HAMILTON",
                    "team_name": "Mercedes"
                }
            ]
        """.trimIndent()

        var capturedUrl = ""
        val client = createMockClient(mockJson) { url ->
            capturedUrl = url
        }

        val drivers = client.getDrivers(driverNumber = 44, sessionKey = "latest")
        assertEquals(1, drivers.size)
        assertEquals("Lewis HAMILTON", drivers.first().fullName)
        assertEquals(44, drivers.first().driverNumber)
        assertTrue(capturedUrl.contains("driver_number=44"))
        assertTrue(capturedUrl.contains("session_key=latest"))
    }

    @Test
    fun testGetIntervals() = runTest {
        val mockJson = """
            [
                {
                    "date": "2023-09-15T13:05:00.000+00:00",
                    "driver_number": 1,
                    "gap_to_leader": 0.0,
                    "interval": 0.0,
                    "meeting_key": 1219,
                    "session_key": 9158
                }
            ]
        """.trimIndent()

        val client = createMockClient(mockJson)
        val intervals = client.getIntervals(sessionKey = 9158, driverNumber = 1)
        assertEquals(1, intervals.size)
        assertEquals(0.0, intervals.first().gapToLeader)
    }

    @Test
    fun testGetLaps() = runTest {
        val mockJson = """
            [
                {
                    "driver_number": 1,
                    "lap_duration": 89.8,
                    "lap_number": 12,
                    "session_key": 9158
                }
            ]
        """.trimIndent()

        val client = createMockClient(mockJson)
        val laps = client.getLaps(sessionKey = 9158, driverNumber = 1)
        assertEquals(1, laps.size)
        assertEquals(12, laps.first().lapNumber)
        assertEquals(89.8, laps.first().lapDuration)
    }

    @Test
    fun testGetLocation() = runTest {
        val mockJson = """
            [
                {
                    "date": "2023-09-15T13:05:00.000+00:00",
                    "driver_number": 1,
                    "meeting_key": 1219,
                    "session_key": 9158,
                    "x": -2350,
                    "y": 1420,
                    "z": 120
                }
            ]
        """.trimIndent()

        val client = createMockClient(mockJson)
        val locations = client.getLocation(sessionKey = 9158, driverNumber = 1)
        assertEquals(1, locations.size)
        assertEquals(-2350, locations.first().x)
    }

    @Test
    fun testGetMeetings() = runTest {
        val mockJson = """
            [
                {
                    "circuit_key": 7,
                    "circuit_short_name": "Spa",
                    "country_code": "BEL",
                    "country_name": "Belgium",
                    "meeting_key": 1219,
                    "meeting_name": "Belgian Grand Prix",
                    "year": 2023
                }
            ]
        """.trimIndent()

        val client = createMockClient(mockJson)
        val meetings = client.getMeetings(year = 2023)
        assertEquals(1, meetings.size)
        assertEquals("Belgian Grand Prix", meetings.first().meetingName)
    }

    @Test
    fun testGetOvertakes() = runTest {
        val mockJson = """
            [
                {
                    "date": "2023-09-15T13:20:00.000+00:00",
                    "meeting_key": 1219,
                    "overtaken_driver_number": 4,
                    "overtaking_driver_number": 63,
                    "position": 1,
                    "session_key": 9636
                }
            ]
        """.trimIndent()

        val client = createMockClient(mockJson)
        val overtakes = client.getOvertakes(sessionKey = 9636, overtakingDriverNumber = 63, overtakenDriverNumber = 4)
        assertEquals(1, overtakes.size)
        assertEquals(63, overtakes.first().overtakingDriverNumber)
        assertEquals(4, overtakes.first().overtakenDriverNumber)
    }

    @Test
    fun testGetPit() = runTest {
        val mockJson = """
            [
                {
                    "date": "2023-09-15T13:30:00.000+00:00",
                    "driver_number": 16,
                    "lane_duration": 18.5,
                    "lap_number": 20,
                    "meeting_key": 1219,
                    "pit_duration": 22.4,
                    "session_key": 9877,
                    "stop_duration": 2.2
                }
            ]
        """.trimIndent()

        val client = createMockClient(mockJson)
        val pits = client.getPit(sessionKey = 9877, driverNumber = 16)
        assertEquals(1, pits.size)
        assertEquals(2.2, pits.first().stopDuration)
        assertEquals(18.5, pits.first().laneDuration)
    }

    @Test
    fun testGetPitStops() = runTest {
        val mockJson = """
            [
                {
                    "driver_number": 16,
                    "lap_number": 20,
                    "pit_duration": 22.4,
                    "session_key": 9158,
                    "stop_number": 1
                }
            ]
        """.trimIndent()

        val client = createMockClient(mockJson)
        val pitStops = client.getPitStops(sessionKey = 9158, driverNumber = 16)
        assertEquals(1, pitStops.size)
        assertEquals(22.4, pitStops.first().pitDuration)
    }

    @Test
    fun testGetPosition() = runTest {
        val mockJson = """
            [
                {
                    "date": "2023-09-15T13:05:00.000+00:00",
                    "driver_number": 1,
                    "meeting_key": 1219,
                    "position": 1,
                    "session_key": 9158
                }
            ]
        """.trimIndent()

        val client = createMockClient(mockJson)
        val positions = client.getPosition(sessionKey = 9158, driverNumber = 1)
        assertEquals(1, positions.size)
        assertEquals(1, positions.first().position)
    }

    @Test
    fun testGetRaceControl() = runTest {
        val mockJson = """
            [
                {
                    "category": "Flag",
                    "flag": "GREEN",
                    "message": "TRACK CLEAR",
                    "session_key": 9158
                }
            ]
        """.trimIndent()

        val client = createMockClient(mockJson)
        val messages = client.getRaceControl(sessionKey = 9158)
        assertEquals(1, messages.size)
        assertEquals("GREEN", messages.first().flag)
    }

    @Test
    fun testGetSessions() = runTest {
        val mockJson = """
            [
                {
                    "circuit_short_name": "Spa",
                    "country_name": "Belgium",
                    "meeting_key": 1219,
                    "session_key": 9158,
                    "session_name": "Race",
                    "session_type": "Race",
                    "year": 2023
                }
            ]
        """.trimIndent()

        val client = createMockClient(mockJson)
        val sessions = client.getSessions(year = 2023)
        assertEquals(1, sessions.size)
        assertEquals("Race", sessions.first().sessionName)
    }

    @Test
    fun testGetStints() = runTest {
        val mockJson = """
            [
                {
                    "compound": "SOFT",
                    "driver_number": 81,
                    "lap_start": 1,
                    "lap_end": 10,
                    "session_key": 9158,
                    "stint_number": 1
                }
            ]
        """.trimIndent()

        val client = createMockClient(mockJson)
        val stints = client.getStints(sessionKey = 9158, driverNumber = 81)
        assertEquals(1, stints.size)
        assertEquals("SOFT", stints.first().compound)
    }

    @Test
    fun testGetTeamRadio() = runTest {
        val mockJson = """
            [
                {
                    "driver_number": 55,
                    "recording_url": "https://api.openf1.org/v1/team_radio?session_key=9158&driver_number=55",
                    "session_key": 9158
                }
            ]
        """.trimIndent()

        val client = createMockClient(mockJson)
        val radios = client.getTeamRadio(sessionKey = 9158, driverNumber = 55)
        assertEquals(1, radios.size)
        assertEquals(55, radios.first().driverNumber)
    }

    @Test
    fun testGetWeather() = runTest {
        val mockJson = """
            [
                {
                    "air_temperature": 21.5,
                    "humidity": 45.0,
                    "pressure": 1013.2,
                    "rainfall": 0,
                    "session_key": 9158,
                    "track_temperature": 32.1,
                    "wind_speed": 4.2
                }
            ]
        """.trimIndent()

        val client = createMockClient(mockJson)
        val weatherList = client.getWeather(sessionKey = 9158)
        assertEquals(1, weatherList.size)
        assertEquals(21.5, weatherList.first().airTemperature)
        assertEquals(32.1, weatherList.first().trackTemperature)
    }

    @Test
    fun testErrorHandling() = runTest {
        val client = createMockClient(
            jsonResponse = """{"error": "Bad request"}""",
            status = HttpStatusCode.BadRequest
        )

        val exception = assertFailsWith<OpenF1Exception> {
            client.getDrivers(sessionKey = 9999)
        }

        assertEquals(400, exception.statusCode)
        assertTrue(exception.message.contains("OpenF1 API error"))
    }

    @Test
    fun testFactoryMethod() {
        val client = OpenF1Client()
        assertTrue(client is DefaultOpenF1Client)
        client.close()
    }
}
