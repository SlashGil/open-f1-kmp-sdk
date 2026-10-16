package com.slashgil.openf1.impl

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
import com.slashgil.openf1.contract.OpenF1Client
import com.slashgil.openf1.exception.OpenF1Exception
import com.slashgil.openf1.model.CarData
import com.slashgil.openf1.model.ChampionshipDriver
import com.slashgil.openf1.model.ChampionshipTeam
import com.slashgil.openf1.model.Driver
import com.slashgil.openf1.model.Interval
import com.slashgil.openf1.model.Lap
import com.slashgil.openf1.model.Location
import com.slashgil.openf1.model.Meeting
import com.slashgil.openf1.model.Overtake
import com.slashgil.openf1.model.Pit
import com.slashgil.openf1.model.PitStop
import com.slashgil.openf1.model.Position
import com.slashgil.openf1.model.RaceControl
import com.slashgil.openf1.model.Session
import com.slashgil.openf1.model.Stint
import com.slashgil.openf1.model.TeamRadio
import com.slashgil.openf1.model.Weather

class DefaultOpenF1Client(
    private val baseUrl: String = OpenF1Client.DEFAULT_BASE_URL,
    private val httpClient: HttpClient = createDefaultHttpClient()
) : OpenF1Client {

    companion object {
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

    override suspend fun getCarData(
        driverNumber: Int?,
        sessionKey: Any?,
        meetingKey: Any?
    ): List<CarData> = request("car_data") {
        parameter("driver_number", driverNumber)
        parameter("session_key", sessionKey?.toString())
        parameter("meeting_key", meetingKey?.toString())
    }

    override suspend fun getChampionshipDrivers(
        driverNumber: Int?,
        sessionKey: Any?,
        meetingKey: Any?
    ): List<ChampionshipDriver> = request("championship_drivers") {
        parameter("driver_number", driverNumber)
        parameter("session_key", sessionKey?.toString())
        parameter("meeting_key", meetingKey?.toString())
    }

    override suspend fun getChampionshipTeams(
        teamName: String?,
        sessionKey: Any?,
        meetingKey: Any?
    ): List<ChampionshipTeam> = request("championship_teams") {
        parameter("team_name", teamName)
        parameter("session_key", sessionKey?.toString())
        parameter("meeting_key", meetingKey?.toString())
    }

    override suspend fun getDrivers(
        driverNumber: Int?,
        sessionKey: Any?,
        meetingKey: Any?,
        teamName: String?
    ): List<Driver> = request("drivers") {
        parameter("driver_number", driverNumber)
        parameter("session_key", sessionKey?.toString())
        parameter("meeting_key", meetingKey?.toString())
        parameter("team_name", teamName)
    }

    override suspend fun getIntervals(
        sessionKey: Any?,
        driverNumber: Int?,
        meetingKey: Any?
    ): List<Interval> = request("intervals") {
        parameter("session_key", sessionKey?.toString())
        parameter("driver_number", driverNumber)
        parameter("meeting_key", meetingKey?.toString())
    }

    override suspend fun getLaps(
        sessionKey: Any?,
        driverNumber: Int?,
        lapNumber: Int?,
        meetingKey: Any?
    ): List<Lap> = request("laps") {
        parameter("session_key", sessionKey?.toString())
        parameter("driver_number", driverNumber)
        parameter("lap_number", lapNumber)
        parameter("meeting_key", meetingKey?.toString())
    }

    override suspend fun getLocation(
        sessionKey: Any?,
        driverNumber: Int?,
        meetingKey: Any?
    ): List<Location> = request("location") {
        parameter("session_key", sessionKey?.toString())
        parameter("driver_number", driverNumber)
        parameter("meeting_key", meetingKey?.toString())
    }

    override suspend fun getMeetings(
        meetingKey: Any?,
        year: Int?,
        countryName: String?,
        countryCode: String?
    ): List<Meeting> = request("meetings") {
        parameter("meeting_key", meetingKey?.toString())
        parameter("year", year)
        parameter("country_name", countryName)
        parameter("country_code", countryCode)
    }

    override suspend fun getOvertakes(
        sessionKey: Any?,
        meetingKey: Any?,
        overtakingDriverNumber: Int?,
        overtakenDriverNumber: Int?,
        position: Int?
    ): List<Overtake> = request("overtakes") {
        parameter("session_key", sessionKey?.toString())
        parameter("meeting_key", meetingKey?.toString())
        parameter("overtaking_driver_number", overtakingDriverNumber)
        parameter("overtaken_driver_number", overtakenDriverNumber)
        parameter("position", position)
    }

    override suspend fun getPit(
        sessionKey: Any?,
        driverNumber: Int?,
        meetingKey: Any?
    ): List<Pit> = request("pit") {
        parameter("session_key", sessionKey?.toString())
        parameter("driver_number", driverNumber)
        parameter("meeting_key", meetingKey?.toString())
    }

    override suspend fun getPitStops(
        sessionKey: Any?,
        driverNumber: Int?,
        meetingKey: Any?
    ): List<PitStop> = request("pit_stops") {
        parameter("session_key", sessionKey?.toString())
        parameter("driver_number", driverNumber)
        parameter("meeting_key", meetingKey?.toString())
    }

    override suspend fun getPosition(
        sessionKey: Any?,
        driverNumber: Int?,
        meetingKey: Any?,
        position: Int?
    ): List<Position> = request("position") {
        parameter("session_key", sessionKey?.toString())
        parameter("driver_number", driverNumber)
        parameter("meeting_key", meetingKey?.toString())
        parameter("position", position)
    }

    override suspend fun getRaceControl(
        sessionKey: Any?,
        driverNumber: Int?,
        meetingKey: Any?,
        flag: String?,
        category: String?
    ): List<RaceControl> = request("race_control") {
        parameter("session_key", sessionKey?.toString())
        parameter("driver_number", driverNumber)
        parameter("meeting_key", meetingKey?.toString())
        parameter("flag", flag)
        parameter("category", category)
    }

    override suspend fun getSessions(
        sessionKey: Any?,
        meetingKey: Any?,
        year: Int?,
        sessionName: String?,
        sessionType: String?
    ): List<Session> = request("sessions") {
        parameter("session_key", sessionKey?.toString())
        parameter("meeting_key", meetingKey?.toString())
        parameter("year", year)
        parameter("session_name", sessionName)
        parameter("session_type", sessionType)
    }

    override suspend fun getStints(
        sessionKey: Any?,
        driverNumber: Int?,
        meetingKey: Any?,
        stintNumber: Int?
    ): List<Stint> = request("stints") {
        parameter("session_key", sessionKey?.toString())
        parameter("driver_number", driverNumber)
        parameter("meeting_key", meetingKey?.toString())
        parameter("stint_number", stintNumber)
    }

    override suspend fun getTeamRadio(
        sessionKey: Any?,
        driverNumber: Int?,
        meetingKey: Any?
    ): List<TeamRadio> = request("team_radio") {
        parameter("session_key", sessionKey?.toString())
        parameter("driver_number", driverNumber)
        parameter("meeting_key", meetingKey?.toString())
    }

    override suspend fun getWeather(
        sessionKey: Any?,
        meetingKey: Any?
    ): List<Weather> = request("weather") {
        parameter("session_key", sessionKey?.toString())
        parameter("meeting_key", meetingKey?.toString())
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
