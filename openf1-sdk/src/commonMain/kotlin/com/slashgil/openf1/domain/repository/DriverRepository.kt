package com.slashgil.openf1.domain.repository

import com.slashgil.openf1.domain.model.DriverProfile
import com.slashgil.openf1.domain.model.DriverStanding
import com.slashgil.openf1.domain.model.TeamStanding
import com.slashgil.openf1.domain.model.specifier.MeetingSpecifier
import com.slashgil.openf1.domain.model.specifier.SessionSpecifier

interface DriverRepository {
    suspend fun getDrivers(
        driverNumber: Int? = null,
        sessionKey: SessionSpecifier? = null,
        meetingKey: MeetingSpecifier? = null,
        teamName: String? = null
    ): List<DriverProfile>

    suspend fun getChampionshipDrivers(
        driverNumber: Int? = null,
        sessionKey: SessionSpecifier? = null,
        meetingKey: MeetingSpecifier? = null
    ): List<DriverStanding>

    suspend fun getChampionshipTeams(
        teamName: String? = null,
        sessionKey: SessionSpecifier? = null,
        meetingKey: MeetingSpecifier? = null
    ): List<TeamStanding>
}
