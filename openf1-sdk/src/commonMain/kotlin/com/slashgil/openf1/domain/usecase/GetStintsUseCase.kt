package com.slashgil.openf1.domain.usecase

import com.slashgil.openf1.domain.model.TyreStint
import com.slashgil.openf1.domain.model.specifier.MeetingSpecifier
import com.slashgil.openf1.domain.model.specifier.SessionSpecifier
import com.slashgil.openf1.domain.repository.RaceEventRepository

class GetStintsUseCase(
    private val raceEventRepository: RaceEventRepository
) {
    suspend operator fun invoke(
        sessionKey: SessionSpecifier? = null,
        driverNumber: Int? = null,
        meetingKey: MeetingSpecifier? = null,
        stintNumber: Int? = null
    ): List<TyreStint> = raceEventRepository.getStints(
        sessionKey = sessionKey,
        driverNumber = driverNumber,
        meetingKey = meetingKey,
        stintNumber = stintNumber
    )
}
