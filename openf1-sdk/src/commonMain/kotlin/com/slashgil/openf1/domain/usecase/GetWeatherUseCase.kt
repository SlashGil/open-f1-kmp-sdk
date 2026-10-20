package com.slashgil.openf1.domain.usecase

import com.slashgil.openf1.domain.model.TrackWeather
import com.slashgil.openf1.domain.model.specifier.MeetingSpecifier
import com.slashgil.openf1.domain.model.specifier.SessionSpecifier
import com.slashgil.openf1.domain.repository.RaceEventRepository

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
}
