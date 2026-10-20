package com.slashgil.openf1.data.repository

import com.slashgil.openf1.contract.OpenF1Client
import com.slashgil.openf1.data.mapper.toDomain
import com.slashgil.openf1.domain.model.DriverInterval
import com.slashgil.openf1.domain.model.DriverPosition
import com.slashgil.openf1.domain.model.LapTiming
import com.slashgil.openf1.domain.model.OvertakeEvent
import com.slashgil.openf1.domain.model.PitLaneEntry
import com.slashgil.openf1.domain.model.PitStop
import com.slashgil.openf1.domain.model.RaceControlMessage
import com.slashgil.openf1.domain.model.TeamRadioMessage
import com.slashgil.openf1.domain.model.TrackWeather
import com.slashgil.openf1.domain.model.TyreStint
import com.slashgil.openf1.domain.model.specifier.MeetingSpecifier
import com.slashgil.openf1.domain.model.specifier.SessionSpecifier
import com.slashgil.openf1.domain.repository.RaceEventRepository

class DefaultRaceEventRepository(
    private val client: OpenF1Client
) : RaceEventRepository {

    override suspend fun getIntervals(
        sessionKey: SessionSpecifier?,
        driverNumber: Int?,
        meetingKey: MeetingSpecifier?
    ): List<DriverInterval> = client.getIntervals(
        sessionKey = sessionKey?.toParamString(),
        driverNumber = driverNumber,
        meetingKey = meetingKey?.toParamString()
    ).map { it.toDomain() }

    override suspend fun getLaps(
        sessionKey: SessionSpecifier?,
        driverNumber: Int?,
        lapNumber: Int?,
        meetingKey: MeetingSpecifier?
    ): List<LapTiming> = client.getLaps(
        sessionKey = sessionKey?.toParamString(),
        driverNumber = driverNumber,
        lapNumber = lapNumber,
        meetingKey = meetingKey?.toParamString()
    ).map { it.toDomain() }

    override suspend fun getOvertakes(
        sessionKey: SessionSpecifier?,
        meetingKey: MeetingSpecifier?,
        overtakingDriverNumber: Int?,
        overtakenDriverNumber: Int?,
        position: Int?
    ): List<OvertakeEvent> = client.getOvertakes(
        sessionKey = sessionKey?.toParamString(),
        meetingKey = meetingKey?.toParamString(),
        overtakingDriverNumber = overtakingDriverNumber,
        overtakenDriverNumber = overtakenDriverNumber,
        position = position
    ).map { it.toDomain() }

    override suspend fun getPit(
        sessionKey: SessionSpecifier?,
        driverNumber: Int?,
        meetingKey: MeetingSpecifier?
    ): List<PitLaneEntry> = client.getPit(
        sessionKey = sessionKey?.toParamString(),
        driverNumber = driverNumber,
        meetingKey = meetingKey?.toParamString()
    ).map { it.toDomain() }

    override suspend fun getPitStops(
        sessionKey: SessionSpecifier?,
        driverNumber: Int?,
        meetingKey: MeetingSpecifier?
    ): List<PitStop> = client.getPitStops(
        sessionKey = sessionKey?.toParamString(),
        driverNumber = driverNumber,
        meetingKey = meetingKey?.toParamString()
    ).map { it.toDomain() }

    override suspend fun getPosition(
        sessionKey: SessionSpecifier?,
        driverNumber: Int?,
        meetingKey: MeetingSpecifier?,
        position: Int?
    ): List<DriverPosition> = client.getPosition(
        sessionKey = sessionKey?.toParamString(),
        driverNumber = driverNumber,
        meetingKey = meetingKey?.toParamString(),
        position = position
    ).map { it.toDomain() }

    override suspend fun getRaceControl(
        sessionKey: SessionSpecifier?,
        driverNumber: Int?,
        meetingKey: MeetingSpecifier?,
        flag: String?,
        category: String?
    ): List<RaceControlMessage> = client.getRaceControl(
        sessionKey = sessionKey?.toParamString(),
        driverNumber = driverNumber,
        meetingKey = meetingKey?.toParamString(),
        flag = flag,
        category = category
    ).map { it.toDomain() }

    override suspend fun getStints(
        sessionKey: SessionSpecifier?,
        driverNumber: Int?,
        meetingKey: MeetingSpecifier?,
        stintNumber: Int?
    ): List<TyreStint> = client.getStints(
        sessionKey = sessionKey?.toParamString(),
        driverNumber = driverNumber,
        meetingKey = meetingKey?.toParamString(),
        stintNumber = stintNumber
    ).map { it.toDomain() }

    override suspend fun getTeamRadio(
        sessionKey: SessionSpecifier?,
        driverNumber: Int?,
        meetingKey: MeetingSpecifier?
    ): List<TeamRadioMessage> = client.getTeamRadio(
        sessionKey = sessionKey?.toParamString(),
        driverNumber = driverNumber,
        meetingKey = meetingKey?.toParamString()
    ).map { it.toDomain() }

    override suspend fun getWeather(
        sessionKey: SessionSpecifier?,
        meetingKey: MeetingSpecifier?
    ): List<TrackWeather> = client.getWeather(
        sessionKey = sessionKey?.toParamString(),
        meetingKey = meetingKey?.toParamString()
    ).map { it.toDomain() }
}
