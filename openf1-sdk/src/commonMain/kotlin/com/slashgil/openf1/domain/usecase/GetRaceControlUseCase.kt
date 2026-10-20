package com.slashgil.openf1.domain.usecase

import com.slashgil.openf1.domain.model.RaceControlMessage
import com.slashgil.openf1.domain.model.specifier.MeetingSpecifier
import com.slashgil.openf1.domain.model.specifier.SessionSpecifier
import com.slashgil.openf1.domain.repository.RaceEventRepository

class GetRaceControlUseCase(
    private val raceEventRepository: RaceEventRepository
) {
    suspend operator fun invoke(
        sessionKey: SessionSpecifier? = null,
        driverNumber: Int? = null,
        meetingKey: MeetingSpecifier? = null,
        flag: String? = null,
        category: String? = null
    ): List<RaceControlMessage> = raceEventRepository.getRaceControl(
        sessionKey = sessionKey,
        driverNumber = driverNumber,
        meetingKey = meetingKey,
        flag = flag,
        category = category
    )
}
