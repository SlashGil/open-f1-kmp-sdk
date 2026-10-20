package com.slashgil.openf1.data.repository

import com.slashgil.openf1.contract.OpenF1Client
import com.slashgil.openf1.data.mapper.toDomain
import com.slashgil.openf1.domain.model.DriverProfile
import com.slashgil.openf1.domain.model.DriverStanding
import com.slashgil.openf1.domain.model.TeamStanding
import com.slashgil.openf1.domain.model.specifier.MeetingSpecifier
import com.slashgil.openf1.domain.model.specifier.SessionSpecifier
import com.slashgil.openf1.domain.repository.DriverRepository

class DefaultDriverRepository(
    private val client: OpenF1Client
) : DriverRepository {

    override suspend fun getDrivers(
        driverNumber: Int?,
        sessionKey: SessionSpecifier?,
        meetingKey: MeetingSpecifier?,
        teamName: String?
    ): List<DriverProfile> = client.getDrivers(
        driverNumber = driverNumber,
        sessionKey = sessionKey?.toParamString(),
        meetingKey = meetingKey?.toParamString(),
        teamName = teamName
    ).map { it.toDomain() }

    override suspend fun getChampionshipDrivers(
        driverNumber: Int?,
        sessionKey: SessionSpecifier?,
        meetingKey: MeetingSpecifier?
    ): List<DriverStanding> = client.getChampionshipDrivers(
        driverNumber = driverNumber,
        sessionKey = sessionKey?.toParamString(),
        meetingKey = meetingKey?.toParamString()
    ).map { it.toDomain() }

    override suspend fun getChampionshipTeams(
        teamName: String?,
        sessionKey: SessionSpecifier?,
        meetingKey: MeetingSpecifier?
    ): List<TeamStanding> = client.getChampionshipTeams(
        teamName = teamName,
        sessionKey = sessionKey?.toParamString(),
        meetingKey = meetingKey?.toParamString()
    ).map { it.toDomain() }
}
