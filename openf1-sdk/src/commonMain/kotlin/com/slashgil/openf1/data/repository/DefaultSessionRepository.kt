package com.slashgil.openf1.data.repository

import com.slashgil.openf1.contract.OpenF1Client
import com.slashgil.openf1.domain.model.GrandPrixMeeting
import com.slashgil.openf1.domain.model.RaceSession
import com.slashgil.openf1.domain.model.specifier.MeetingSpecifier
import com.slashgil.openf1.domain.model.specifier.SessionSpecifier
import com.slashgil.openf1.domain.repository.SessionRepository

class DefaultSessionRepository(
    private val client: OpenF1Client
) : SessionRepository {

    override suspend fun getSessions(
        year: Int?,
        sessionName: String?,
        sessionType: String?,
        meetingKey: MeetingSpecifier?,
        sessionKey: SessionSpecifier?
    ): List<RaceSession> = client.getSessions(
        sessionKey = sessionKey?.toParamString(),
        meetingKey = meetingKey?.toParamString(),
        year = year,
        sessionName = sessionName,
        sessionType = sessionType
    ).map { RaceSession.from(it) }

    override suspend fun getMeetings(
        meetingKey: MeetingSpecifier?,
        year: Int?,
        countryName: String?,
        countryCode: String?
    ): List<GrandPrixMeeting> = client.getMeetings(
        meetingKey = meetingKey?.toParamString(),
        year = year,
        countryName = countryName,
        countryCode = countryCode
    ).map { GrandPrixMeeting.from(it) }
}
