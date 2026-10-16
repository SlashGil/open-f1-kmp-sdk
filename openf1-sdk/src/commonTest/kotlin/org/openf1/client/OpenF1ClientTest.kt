package org.openf1.client

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
import org.openf1.exception.OpenF1Exception
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertTrue

class OpenF1ClientTest {

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

        return OpenF1Client(
            baseUrl = "https://api.openf1.org/v1",
            httpClient = httpClient
        )
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

        val drivers = client.getDrivers(driverNumber = 44, sessionKey = 9158)
        assertEquals(1, drivers.size)
        assertEquals("Lewis HAMILTON", drivers.first().fullName)
        assertEquals(44, drivers.first().driverNumber)
        assertTrue(capturedUrl.contains("driver_number=44"))
        assertTrue(capturedUrl.contains("session_key=9158"))
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
}
