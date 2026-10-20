package com.slashgil.openf1.domain.repository

import com.slashgil.openf1.domain.model.GrandPrixMeeting
import com.slashgil.openf1.domain.model.RaceSession
import com.slashgil.openf1.domain.model.specifier.MeetingSpecifier
import com.slashgil.openf1.domain.model.specifier.SessionSpecifier

interface SessionRepository {
    suspend fun getSessions(
        year: Int? = null,
        sessionName: String? = null,
        sessionType: String? = null,
        meetingKey: MeetingSpecifier? = null,
        sessionKey: SessionSpecifier? = null
    ): List<RaceSession>

    suspend fun getMeetings(
        meetingKey: MeetingSpecifier? = null,
        year: Int? = null,
        countryName: String? = null,
        countryCode: String? = null
    ): List<GrandPrixMeeting>
}
