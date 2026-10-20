package com.slashgil.openf1.domain.usecase

import com.slashgil.openf1.domain.model.LapTiming
import com.slashgil.openf1.domain.model.specifier.MeetingSpecifier
import com.slashgil.openf1.domain.model.specifier.SessionSpecifier
import com.slashgil.openf1.domain.repository.RaceEventRepository

class GetLapsUseCase(
    private val raceEventRepository: RaceEventRepository
) {
    suspend operator fun invoke(
        sessionKey: SessionSpecifier? = null,
        driverNumber: Int? = null,
        lapNumber: Int? = null,
        meetingKey: MeetingSpecifier? = null
    ): List<LapTiming> = raceEventRepository.getLaps(
        sessionKey = sessionKey,
        driverNumber = driverNumber,
        lapNumber = lapNumber,
        meetingKey = meetingKey
    )
}
