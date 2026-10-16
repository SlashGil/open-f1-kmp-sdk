package org.openf1.client

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.logging.SIMPLE
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.statement.HttpResponse
import io.ktor.http.isSuccess
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.openf1.exception.OpenF1Exception
import org.openf1.model.Driver
import org.openf1.model.Lap
import org.openf1.model.PitStop
import org.openf1.model.Stint
import org.openf1.model.TeamRadio

class OpenF1Client(
    private val baseUrl: String = DEFAULT_BASE_URL,
    private val httpClient: HttpClient = createDefaultHttpClient()
) : AutoCloseable {

    companion object {
        const val DEFAULT_BASE_URL = "https://api.openf1.org/v1"

        fun createDefaultHttpClient(): HttpClient = HttpClient {
            install(ContentNegotiation) {
                json(Json {
                    ignoreUnknownKeys = true
                    isLenient = true
                    encodeDefaults = true
                })
            }
            install(Logging) {
                logger = Logger.SIMPLE
                level = LogLevel.NONE
            }
        }
    }

    /**
     * GET /drivers
     * Returns a list of drivers matching the provided criteria.
     */
    suspend fun getDrivers(
        driverNumber: Int? = null,
        sessionKey: Int? = null,
        meetingKey: Int? = null,
        teamName: String? = null
    ): List<Driver> = request("drivers") {
        parameter("driver_number", driverNumber)
        parameter("session_key", sessionKey)
        parameter("meeting_key", meetingKey)
        parameter("team_name", teamName)
    }

    /**
     * GET /laps
     * Returns a list of lap detailed timing data matching the criteria.
     */
    suspend fun getLaps(
        sessionKey: Int? = null,
        driverNumber: Int? = null,
        lapNumber: Int? = null,
        meetingKey: Int? = null
    ): List<Lap> = request("laps") {
        parameter("session_key", sessionKey)
        parameter("driver_number", driverNumber)
        parameter("lap_number", lapNumber)
        parameter("meeting_key", meetingKey)
    }

    /**
     * GET /pit_stops
     * Returns pit stop events matching the criteria.
     */
    suspend fun getPitStops(
        sessionKey: Int? = null,
        driverNumber: Int? = null,
        meetingKey: Int? = null
    ): List<PitStop> = request("pit_stops") {
        parameter("session_key", sessionKey)
        parameter("driver_number", driverNumber)
        parameter("meeting_key", meetingKey)
    }

    /**
     * GET /stints
     * Returns tyre stint data matching the criteria.
     */
    suspend fun getStints(
        sessionKey: Int? = null,
        driverNumber: Int? = null,
        meetingKey: Int? = null,
        stintNumber: Int? = null
    ): List<Stint> = request("stints") {
        parameter("session_key", sessionKey)
        parameter("driver_number", driverNumber)
        parameter("meeting_key", meetingKey)
        parameter("stint_number", stintNumber)
    }

    /**
     * GET /team_radio
     * Returns team radio audio recording entries matching the criteria.
     */
    suspend fun getTeamRadio(
        sessionKey: Int? = null,
        driverNumber: Int? = null,
        meetingKey: Int? = null
    ): List<TeamRadio> = request("team_radio") {
        parameter("session_key", sessionKey)
        parameter("driver_number", driverNumber)
        parameter("meeting_key", meetingKey)
    }

    private suspend inline fun <reified T> request(
        endpoint: String,
        crossinline block: io.ktor.client.request.HttpRequestBuilder.() -> Unit = {}
    ): T {
        val url = "$baseUrl/$endpoint"
        try {
            val response: HttpResponse = httpClient.get(url) {
                block()
            }
            if (!response.status.isSuccess()) {
                throw OpenF1Exception(
                    statusCode = response.status.value,
                    message = "OpenF1 API error on $endpoint: ${response.status.value} ${response.status.description}"
                )
            }
            return response.body()
        } catch (e: OpenF1Exception) {
            throw e
        } catch (e: Exception) {
            throw OpenF1Exception(
                message = "Failed to execute OpenF1 request to $endpoint: ${e.message}",
                cause = e
            )
        }
    }

    override fun close() {
        httpClient.close()
    }
}
