package com.slashgil.openf1.domain.usecase

import com.slashgil.openf1.domain.model.TrackWeather
import com.slashgil.openf1.domain.model.specifier.MeetingSpecifier
import com.slashgil.openf1.domain.model.specifier.SessionSpecifier
import com.slashgil.openf1.domain.repository.RaceEventRepository
import com.slashgil.openf1.domain.result.Result
import com.slashgil.openf1.domain.result.asResult

class GetWeatherUseCase(
    private val raceEventRepository: RaceEventRepository
) {
    suspend operator fun invoke(
        sessionKey: SessionSpecifier? = null,
        meetingKey: MeetingSpecifier? = null
    ): List<TrackWeather> = raceEventRepository.getWeather(
        sessionKey = sessionKey,
        meetingKey = meetingKey
    )

    suspend fun asResult(
        sessionKey: SessionSpecifier? = null,
        meetingKey: MeetingSpecifier? = null
    ): Result<List<TrackWeather>> = asResult {
        invoke(sessionKey, meetingKey)
    }
}
