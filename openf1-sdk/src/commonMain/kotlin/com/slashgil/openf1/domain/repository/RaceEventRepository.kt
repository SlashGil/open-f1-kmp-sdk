package com.slashgil.openf1.domain.repository

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

interface RaceEventRepository {
    suspend fun getIntervals(
        sessionKey: SessionSpecifier? = null,
        driverNumber: Int? = null,
        meetingKey: MeetingSpecifier? = null
    ): List<DriverInterval>

    suspend fun getLaps(
        sessionKey: SessionSpecifier? = null,
        driverNumber: Int? = null,
        lapNumber: Int? = null,
        meetingKey: MeetingSpecifier? = null
    ): List<LapTiming>

    suspend fun getOvertakes(
        sessionKey: SessionSpecifier? = null,
        meetingKey: MeetingSpecifier? = null,
        overtakingDriverNumber: Int? = null,
        overtakenDriverNumber: Int? = null,
        position: Int? = null
    ): List<OvertakeEvent>

    suspend fun getPit(
        sessionKey: SessionSpecifier? = null,
        driverNumber: Int? = null,
        meetingKey: MeetingSpecifier? = null
    ): List<PitLaneEntry>

    suspend fun getPitStops(
        sessionKey: SessionSpecifier? = null,
        driverNumber: Int? = null,
        meetingKey: MeetingSpecifier? = null
    ): List<PitStop>

    suspend fun getPosition(
        sessionKey: SessionSpecifier? = null,
        driverNumber: Int? = null,
        meetingKey: MeetingSpecifier? = null,
        position: Int? = null
    ): List<DriverPosition>

    suspend fun getRaceControl(
        sessionKey: SessionSpecifier? = null,
        driverNumber: Int? = null,
        meetingKey: MeetingSpecifier? = null,
        flag: String? = null,
        category: String? = null
    ): List<RaceControlMessage>

    suspend fun getStints(
        sessionKey: SessionSpecifier? = null,
        driverNumber: Int? = null,
        meetingKey: MeetingSpecifier? = null,
        stintNumber: Int? = null
    ): List<TyreStint>

    suspend fun getTeamRadio(
        sessionKey: SessionSpecifier? = null,
        driverNumber: Int? = null,
        meetingKey: MeetingSpecifier? = null
    ): List<TeamRadioMessage>

    suspend fun getWeather(
        sessionKey: SessionSpecifier? = null,
        meetingKey: MeetingSpecifier? = null
    ): List<TrackWeather>
}
