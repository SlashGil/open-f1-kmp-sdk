package com.slashgil.openf1.contract

import io.ktor.client.HttpClient
import com.slashgil.openf1.impl.DefaultOpenF1Client
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

/**
 * Complete Interface contract for the official OpenF1 REST API v1 client.
 * Documentation: https://openf1.org/docs/#api-endpoints
 */
interface OpenF1Client : AutoCloseable {

    /**
     * GET /car_data
     * Returns telemetry data for cars (speed, rpm, pedal inputs, drs, etc.) at ~3.7 Hz.
     */
    suspend fun getCarData(
        driverNumber: Int? = null,
        sessionKey: Any? = null,
        meetingKey: Any? = null
    ): List<CarData>

    /**
     * GET /championship_drivers
     * Returns drivers' championship standings during or after race sessions.
     */
    suspend fun getChampionshipDrivers(
        driverNumber: Int? = null,
        sessionKey: Any? = null,
        meetingKey: Any? = null
    ): List<ChampionshipDriver>

    /**
     * GET /championship_teams
     * Returns teams' championship standings during or after race sessions.
     */
    suspend fun getChampionshipTeams(
        teamName: String? = null,
        sessionKey: Any? = null,
        meetingKey: Any? = null
    ): List<ChampionshipTeam>

    /**
     * GET /drivers
     * Returns information about drivers participating in a session.
     */
    suspend fun getDrivers(
        driverNumber: Int? = null,
        sessionKey: Any? = null,
        meetingKey: Any? = null,
        teamName: String? = null
    ): List<Driver>

    /**
     * GET /intervals
     * Returns real-time interval data between drivers and gap to the leader.
     */
    suspend fun getIntervals(
        sessionKey: Any? = null,
        driverNumber: Int? = null,
        meetingKey: Any? = null
    ): List<Interval>

    /**
     * GET /laps
     * Returns lap times, sector durations, mini-sectors, and speeds.
     */
    suspend fun getLaps(
        sessionKey: Any? = null,
        driverNumber: Int? = null,
        lapNumber: Int? = null,
        meetingKey: Any? = null
    ): List<Lap>

    /**
     * GET /location
     * Returns 3D Cartesian coordinates (x, y, z) of cars on the track.
     */
    suspend fun getLocation(
        sessionKey: Any? = null,
        driverNumber: Int? = null,
        meetingKey: Any? = null
    ): List<Location>

    /**
     * GET /meetings
     * Returns information about Grand Prix / testing event weekends.
     */
    suspend fun getMeetings(
        meetingKey: Any? = null,
        year: Int? = null,
        countryName: String? = null,
        countryCode: String? = null
    ): List<Meeting>

    /**
     * GET /overtakes
     * Returns position changes and overtake records during races.
     */
    suspend fun getOvertakes(
        sessionKey: Any? = null,
        meetingKey: Any? = null,
        overtakingDriverNumber: Int? = null,
        overtakenDriverNumber: Int? = null,
        position: Int? = null
    ): List<Overtake>

    /**
     * GET /pit
     * Returns pit stop events and pit lane durations.
     */
    suspend fun getPit(
        sessionKey: Any? = null,
        driverNumber: Int? = null,
        meetingKey: Any? = null
    ): List<Pit>

    /**
     * GET /pit_stops
     * Convenience alias for pit stop events.
     */
    suspend fun getPitStops(
        sessionKey: Any? = null,
        driverNumber: Int? = null,
        meetingKey: Any? = null
    ): List<PitStop>

    /**
     * GET /position
     * Returns driver position history throughout a session.
     */
    suspend fun getPosition(
        sessionKey: Any? = null,
        driverNumber: Int? = null,
        meetingKey: Any? = null,
        position: Int? = null
    ): List<Position>

    /**
     * GET /race_control
     * Returns race control messages, flags, incidents, and session statuses.
     */
    suspend fun getRaceControl(
        sessionKey: Any? = null,
        driverNumber: Int? = null,
        meetingKey: Any? = null,
        flag: String? = null,
        category: String? = null
    ): List<RaceControl>

    /**
     * GET /sessions
     * Returns session metadata (type, location, dates).
     */
    suspend fun getSessions(
        sessionKey: Any? = null,
        meetingKey: Any? = null,
        year: Int? = null,
        sessionName: String? = null,
        sessionType: String? = null
    ): List<Session>

    /**
     * GET /stints
     * Returns tyre stint data and compound information.
     */
    suspend fun getStints(
        sessionKey: Any? = null,
        driverNumber: Int? = null,
        meetingKey: Any? = null,
        stintNumber: Int? = null
    ): List<Stint>

    /**
     * GET /team_radio
     * Returns team radio audio recording entries.
     */
    suspend fun getTeamRadio(
        sessionKey: Any? = null,
        driverNumber: Int? = null,
        meetingKey: Any? = null
    ): List<TeamRadio>

    /**
     * GET /weather
     * Returns track weather condition data (air/track temp, humidity, rainfall).
     */
    suspend fun getWeather(
        sessionKey: Any? = null,
        meetingKey: Any? = null
    ): List<Weather>

    companion object {
        const val DEFAULT_BASE_URL = "https://api.openf1.org/v1"

        /**
         * Factory function to instantiate the default [OpenF1Client] implementation.
         */
        operator fun invoke(
            baseUrl: String = DEFAULT_BASE_URL,
            httpClient: HttpClient = createDefaultHttpClient()
        ): OpenF1Client = DefaultOpenF1Client(baseUrl, httpClient)

        fun createDefaultHttpClient(): HttpClient = DefaultOpenF1Client.createDefaultHttpClient()
    }
}
