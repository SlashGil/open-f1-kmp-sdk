package com.slashgil.openf1.di

import io.ktor.client.HttpClient
import com.slashgil.openf1.contract.OpenF1Client
import com.slashgil.openf1.data.repository.DefaultDriverRepository
import com.slashgil.openf1.data.repository.DefaultRaceEventRepository
import com.slashgil.openf1.data.repository.DefaultSessionRepository
import com.slashgil.openf1.data.repository.DefaultTelemetryRepository
import com.slashgil.openf1.domain.repository.DriverRepository
import com.slashgil.openf1.domain.repository.RaceEventRepository
import com.slashgil.openf1.domain.repository.SessionRepository
import com.slashgil.openf1.domain.repository.TelemetryRepository
import com.slashgil.openf1.domain.usecase.GetCarDataUseCase
import com.slashgil.openf1.domain.usecase.GetChampionshipDriversUseCase
import com.slashgil.openf1.domain.usecase.GetChampionshipTeamsUseCase
import com.slashgil.openf1.domain.usecase.GetDriversUseCase
import com.slashgil.openf1.domain.usecase.GetIntervalsUseCase
import com.slashgil.openf1.domain.usecase.GetLapsUseCase
import com.slashgil.openf1.domain.usecase.GetLocationUseCase
import com.slashgil.openf1.domain.usecase.GetMeetingsUseCase
import com.slashgil.openf1.domain.usecase.GetOvertakesUseCase
import com.slashgil.openf1.domain.usecase.GetPitUseCase
import com.slashgil.openf1.domain.usecase.GetPitStopsUseCase
import com.slashgil.openf1.domain.usecase.GetPositionUseCase
import com.slashgil.openf1.domain.usecase.GetRaceControlUseCase
import com.slashgil.openf1.domain.usecase.GetSessionLeaderboardUseCase
import com.slashgil.openf1.domain.usecase.GetSessionsUseCase
import com.slashgil.openf1.domain.usecase.GetStintsUseCase
import com.slashgil.openf1.domain.usecase.GetTeamRadioUseCase
import com.slashgil.openf1.domain.usecase.GetWeatherUseCase

/**
 * Pure Kotlin SDK Container providing Dependency Injection without forcing third-party DI frameworks on consumers.
 */
class OpenF1Sdk private constructor(
    val client: OpenF1Client,
    val sessionRepository: SessionRepository,
    val driverRepository: DriverRepository,
    val telemetryRepository: TelemetryRepository,
    val raceEventRepository: RaceEventRepository,
    val getCarDataUseCase: GetCarDataUseCase,
    val getChampionshipDriversUseCase: GetChampionshipDriversUseCase,
    val getChampionshipTeamsUseCase: GetChampionshipTeamsUseCase,
    val getDriversUseCase: GetDriversUseCase,
    val getIntervalsUseCase: GetIntervalsUseCase,
    val getLapsUseCase: GetLapsUseCase,
    val getLocationUseCase: GetLocationUseCase,
    val getMeetingsUseCase: GetMeetingsUseCase,
    val getOvertakesUseCase: GetOvertakesUseCase,
    val getPitUseCase: GetPitUseCase,
    val getPitStopsUseCase: GetPitStopsUseCase,
    val getPositionUseCase: GetPositionUseCase,
    val getRaceControlUseCase: GetRaceControlUseCase,
    val getSessionsUseCase: GetSessionsUseCase,
    val getStintsUseCase: GetStintsUseCase,
    val getTeamRadioUseCase: GetTeamRadioUseCase,
    val getWeatherUseCase: GetWeatherUseCase,
    val getSessionLeaderboardUseCase: GetSessionLeaderboardUseCase
) : AutoCloseable {

    override fun close() {
        client.close()
    }

    companion object {
        fun create(
            baseUrl: String = OpenF1Client.DEFAULT_BASE_URL,
            httpClient: HttpClient = OpenF1Client.createDefaultHttpClient()
        ): OpenF1Sdk {
            val client = OpenF1Client(baseUrl, httpClient)
            val sessionRepo = DefaultSessionRepository(client)
            val driverRepo = DefaultDriverRepository(client)
            val telemetryRepo = DefaultTelemetryRepository(client)
            val raceEventRepo = DefaultRaceEventRepository(client)

            return OpenF1Sdk(
                client = client,
                sessionRepository = sessionRepo,
                driverRepository = driverRepo,
                telemetryRepository = telemetryRepo,
                raceEventRepository = raceEventRepo,
                getCarDataUseCase = GetCarDataUseCase(telemetryRepo),
                getChampionshipDriversUseCase = GetChampionshipDriversUseCase(driverRepo),
                getChampionshipTeamsUseCase = GetChampionshipTeamsUseCase(driverRepo),
                getDriversUseCase = GetDriversUseCase(driverRepo),
                getIntervalsUseCase = GetIntervalsUseCase(raceEventRepo),
                getLapsUseCase = GetLapsUseCase(raceEventRepo),
                getLocationUseCase = GetLocationUseCase(telemetryRepo),
                getMeetingsUseCase = GetMeetingsUseCase(sessionRepo),
                getOvertakesUseCase = GetOvertakesUseCase(raceEventRepo),
                getPitUseCase = GetPitUseCase(raceEventRepo),
                getPitStopsUseCase = GetPitStopsUseCase(raceEventRepo),
                getPositionUseCase = GetPositionUseCase(raceEventRepo),
                getRaceControlUseCase = GetRaceControlUseCase(raceEventRepo),
                getSessionsUseCase = GetSessionsUseCase(sessionRepo),
                getStintsUseCase = GetStintsUseCase(raceEventRepo),
                getTeamRadioUseCase = GetTeamRadioUseCase(raceEventRepo),
                getWeatherUseCase = GetWeatherUseCase(raceEventRepo),
                getSessionLeaderboardUseCase = GetSessionLeaderboardUseCase(driverRepo, raceEventRepo)
            )
        }
    }
}
