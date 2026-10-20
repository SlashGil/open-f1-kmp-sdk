package com.slashgil.openf1.domain.usecase

import com.slashgil.openf1.domain.model.TeamRadioMessage
import com.slashgil.openf1.domain.model.specifier.MeetingSpecifier
import com.slashgil.openf1.domain.model.specifier.SessionSpecifier
import com.slashgil.openf1.domain.repository.RaceEventRepository
import com.slashgil.openf1.domain.result.Result
import com.slashgil.openf1.domain.result.asResult

class GetTeamRadioUseCase(
    private val raceEventRepository: RaceEventRepository
) {
    suspend operator fun invoke(
        sessionKey: SessionSpecifier? = null,
        driverNumber: Int? = null,
        meetingKey: MeetingSpecifier? = null
    ): List<TeamRadioMessage> = raceEventRepository.getTeamRadio(
        sessionKey = sessionKey,
        driverNumber = driverNumber,
        meetingKey = meetingKey
    )

    suspend fun asResult(
        sessionKey: SessionSpecifier? = null,
        driverNumber: Int? = null,
        meetingKey: MeetingSpecifier? = null
    ): Result<List<TeamRadioMessage>> = asResult {
        invoke(sessionKey, driverNumber, meetingKey)
    }
}
