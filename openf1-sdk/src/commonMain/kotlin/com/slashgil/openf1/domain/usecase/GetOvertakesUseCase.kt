package com.slashgil.openf1.domain.usecase

import com.slashgil.openf1.domain.model.OvertakeEvent
import com.slashgil.openf1.domain.model.specifier.MeetingSpecifier
import com.slashgil.openf1.domain.model.specifier.SessionSpecifier
import com.slashgil.openf1.domain.repository.RaceEventRepository

class GetOvertakesUseCase(
    private val raceEventRepository: RaceEventRepository
) {
    suspend operator fun invoke(
        sessionKey: SessionSpecifier? = null,
        meetingKey: MeetingSpecifier? = null,
        overtakingDriverNumber: Int? = null,
        overtakenDriverNumber: Int? = null,
        position: Int? = null
    ): List<OvertakeEvent> = raceEventRepository.getOvertakes(
        sessionKey = sessionKey,
        meetingKey = meetingKey,
        overtakingDriverNumber = overtakingDriverNumber,
        overtakenDriverNumber = overtakenDriverNumber,
        position = position
    )
}
