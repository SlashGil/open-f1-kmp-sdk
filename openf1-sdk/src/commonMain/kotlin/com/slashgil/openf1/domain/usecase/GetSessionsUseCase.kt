package com.slashgil.openf1.domain.usecase

import com.slashgil.openf1.domain.model.RaceSession
import com.slashgil.openf1.domain.model.specifier.MeetingSpecifier
import com.slashgil.openf1.domain.model.specifier.SessionSpecifier
import com.slashgil.openf1.domain.repository.SessionRepository

class GetSessionsUseCase(
    private val sessionRepository: SessionRepository
) {
    suspend operator fun invoke(
        sessionKey: SessionSpecifier? = null,
        meetingKey: MeetingSpecifier? = null,
        year: Int? = null,
        sessionName: String? = null,
        sessionType: String? = null
    ): List<RaceSession> = sessionRepository.getSessions(
        sessionKey = sessionKey,
        meetingKey = meetingKey,
        year = year,
        sessionName = sessionName,
        sessionType = sessionType
    )
}
