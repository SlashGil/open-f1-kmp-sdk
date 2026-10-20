package com.slashgil.openf1.domain.usecase

import com.slashgil.openf1.domain.model.DriverPosition
import com.slashgil.openf1.domain.model.specifier.MeetingSpecifier
import com.slashgil.openf1.domain.model.specifier.SessionSpecifier
import com.slashgil.openf1.domain.repository.RaceEventRepository

class GetPositionUseCase(
    private val raceEventRepository: RaceEventRepository
) {
    suspend operator fun invoke(
        sessionKey: SessionSpecifier? = null,
        driverNumber: Int? = null,
        meetingKey: MeetingSpecifier? = null,
        position: Int? = null
    ): List<DriverPosition> = raceEventRepository.getPosition(
        sessionKey = sessionKey,
        driverNumber = driverNumber,
        meetingKey = meetingKey,
        position = position
    )
}
