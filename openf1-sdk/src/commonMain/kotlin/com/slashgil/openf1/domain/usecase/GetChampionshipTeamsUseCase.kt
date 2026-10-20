package com.slashgil.openf1.domain.usecase

import com.slashgil.openf1.domain.model.TeamStanding
import com.slashgil.openf1.domain.model.specifier.MeetingSpecifier
import com.slashgil.openf1.domain.model.specifier.SessionSpecifier
import com.slashgil.openf1.domain.repository.DriverRepository

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
}
