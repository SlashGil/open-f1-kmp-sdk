package com.slashgil.openf1.domain.usecase

import com.slashgil.openf1.domain.model.TeamStanding
import com.slashgil.openf1.domain.model.specifier.MeetingSpecifier
import com.slashgil.openf1.domain.model.specifier.SessionSpecifier
import com.slashgil.openf1.domain.repository.DriverRepository
import com.slashgil.openf1.domain.result.Result
import com.slashgil.openf1.domain.result.asResult

class GetChampionshipTeamsUseCase(
    private val driverRepository: DriverRepository
) {
    suspend operator fun invoke(
        teamName: String? = null,
        sessionKey: SessionSpecifier? = null,
        meetingKey: MeetingSpecifier? = null
    ): List<TeamStanding> = driverRepository.getChampionshipTeams(
        teamName = teamName,
        sessionKey = sessionKey,
        meetingKey = meetingKey
    )

    suspend fun asResult(
        teamName: String? = null,
        sessionKey: SessionSpecifier? = null,
        meetingKey: MeetingSpecifier? = null
    ): Result<List<TeamStanding>> = com.slashgil.openf1.domain.result.asResult {
        invoke(teamName, sessionKey, meetingKey)
    }
}
