package com.slashgil.openf1.domain.usecase

import com.slashgil.openf1.domain.model.TyreStint
import com.slashgil.openf1.domain.model.specifier.MeetingSpecifier
import com.slashgil.openf1.domain.model.specifier.SessionSpecifier
import com.slashgil.openf1.domain.repository.RaceEventRepository
import com.slashgil.openf1.domain.result.Result
import com.slashgil.openf1.domain.result.asResult

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

    suspend fun asResult(
        sessionKey: SessionSpecifier? = null,
        driverNumber: Int? = null,
        meetingKey: MeetingSpecifier? = null,
        stintNumber: Int? = null
    ): Result<List<TyreStint>> = asResult {
        invoke(sessionKey, driverNumber, meetingKey, stintNumber)
    }
}
