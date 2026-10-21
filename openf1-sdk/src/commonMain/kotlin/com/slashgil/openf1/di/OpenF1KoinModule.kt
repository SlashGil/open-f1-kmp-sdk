package com.slashgil.openf1.di

import io.ktor.client.HttpClient
import org.koin.dsl.module
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
import com.slashgil.openf1.impl.DefaultOpenF1Client

/**
 * Pre-configured Koin Multiplatform Module providing singletons for OpenF1Client, Repositories, and Use Cases.
 */
fun openF1Module(
    baseUrl: String = OpenF1Client.DEFAULT_BASE_URL,
    httpClientBuilder: (() -> HttpClient)? = null
) = module {
    single<OpenF1Client> {
        val customHttpClient = httpClientBuilder?.invoke() ?: OpenF1Client.createDefaultHttpClient()
        DefaultOpenF1Client(baseUrl, customHttpClient)
    }

    single<SessionRepository> { DefaultSessionRepository(get()) }
    single<DriverRepository> { DefaultDriverRepository(get()) }
    single<TelemetryRepository> { DefaultTelemetryRepository(get()) }
    single<RaceEventRepository> { DefaultRaceEventRepository(get()) }

    factory { GetCarDataUseCase(get()) }
    factory { GetChampionshipDriversUseCase(get()) }
    factory { GetChampionshipTeamsUseCase(get()) }
    factory { GetDriversUseCase(get()) }
    factory { GetIntervalsUseCase(get()) }
    factory { GetLapsUseCase(get()) }
    factory { GetLocationUseCase(get()) }
    factory { GetMeetingsUseCase(get()) }
    factory { GetOvertakesUseCase(get()) }
    factory { GetPitUseCase(get()) }
    factory { GetPitStopsUseCase(get()) }
    factory { GetPositionUseCase(get()) }
    factory { GetRaceControlUseCase(get()) }
    factory { GetSessionsUseCase(get()) }
    factory { GetStintsUseCase(get()) }
    factory { GetTeamRadioUseCase(get()) }
    factory { GetWeatherUseCase(get()) }
    factory { GetSessionLeaderboardUseCase(get(), get()) }
}
