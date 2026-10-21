package com.slashgil.openf1.di

import com.slashgil.openf1.contract.OpenF1Client
import com.slashgil.openf1.domain.repository.DriverRepository
import com.slashgil.openf1.domain.repository.RaceEventRepository
import com.slashgil.openf1.domain.repository.SessionRepository
import com.slashgil.openf1.domain.repository.TelemetryRepository
import com.slashgil.openf1.domain.usecase.GetCarDataUseCase
import com.slashgil.openf1.domain.usecase.GetDriversUseCase
import com.slashgil.openf1.domain.usecase.GetSessionLeaderboardUseCase
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import kotlin.test.AfterTest
import kotlin.test.Test
import kotlin.test.assertNotNull

class DependencyInjectionTest {

    @AfterTest
    fun tearDown() {
        stopKoin()
    }

    @Test
    fun testOpenF1SdkPureKotlinContainerInitialization() {
        val sdk = OpenF1Sdk.create()
        assertNotNull(sdk.client)
        assertNotNull(sdk.sessionRepository)
        assertNotNull(sdk.driverRepository)
        assertNotNull(sdk.telemetryRepository)
        assertNotNull(sdk.raceEventRepository)

        assertNotNull(sdk.getCarDataUseCase)
        assertNotNull(sdk.getChampionshipDriversUseCase)
        assertNotNull(sdk.getChampionshipTeamsUseCase)
        assertNotNull(sdk.getDriversUseCase)
        assertNotNull(sdk.getIntervalsUseCase)
        assertNotNull(sdk.getLapsUseCase)
        assertNotNull(sdk.getLocationUseCase)
        assertNotNull(sdk.getMeetingsUseCase)
        assertNotNull(sdk.getOvertakesUseCase)
        assertNotNull(sdk.getPitUseCase)
        assertNotNull(sdk.getPitStopsUseCase)
        assertNotNull(sdk.getPositionUseCase)
        assertNotNull(sdk.getRaceControlUseCase)
        assertNotNull(sdk.getSessionsUseCase)
        assertNotNull(sdk.getStintsUseCase)
        assertNotNull(sdk.getTeamRadioUseCase)
        assertNotNull(sdk.getWeatherUseCase)
        assertNotNull(sdk.getSessionLeaderboardUseCase)

        sdk.close()
    }

    @Test
    fun testOpenF1KoinModuleResolution() {
        val koinApp = startKoin {
            modules(openF1Module())
        }
        val koin = koinApp.koin

        assertNotNull(koin.get<OpenF1Client>())
        assertNotNull(koin.get<SessionRepository>())
        assertNotNull(koin.get<DriverRepository>())
        assertNotNull(koin.get<TelemetryRepository>())
        assertNotNull(koin.get<RaceEventRepository>())

        assertNotNull(koin.get<GetCarDataUseCase>())
        assertNotNull(koin.get<GetDriversUseCase>())
        assertNotNull(koin.get<GetSessionLeaderboardUseCase>())
    }
}
