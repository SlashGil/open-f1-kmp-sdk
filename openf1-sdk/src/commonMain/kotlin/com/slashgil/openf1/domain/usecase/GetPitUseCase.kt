package com.slashgil.openf1.domain.usecase

import com.slashgil.openf1.domain.model.PitLaneEntry
import com.slashgil.openf1.domain.model.specifier.MeetingSpecifier
import com.slashgil.openf1.domain.model.specifier.SessionSpecifier
import com.slashgil.openf1.domain.repository.RaceEventRepository

class GetPitUseCase(
    private val raceEventRepository: RaceEventRepository
) {
    suspend operator fun invoke(
        sessionKey: SessionSpecifier? = null,
        driverNumber: Int? = null,
        meetingKey: MeetingSpecifier? = null
    ): List<PitLaneEntry> = raceEventRepository.getPit(
        sessionKey = sessionKey,
        driverNumber = driverNumber,
        meetingKey = meetingKey
    )
}
