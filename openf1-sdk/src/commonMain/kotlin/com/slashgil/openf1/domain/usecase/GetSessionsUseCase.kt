package com.slashgil.openf1.domain.usecase

import com.slashgil.openf1.domain.model.RaceSession
import com.slashgil.openf1.domain.model.specifier.MeetingSpecifier
import com.slashgil.openf1.domain.model.specifier.SessionSpecifier
import com.slashgil.openf1.domain.repository.SessionRepository
import com.slashgil.openf1.domain.result.Result
import com.slashgil.openf1.domain.result.asResult

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

    suspend fun asResult(
        sessionKey: SessionSpecifier? = null,
        meetingKey: MeetingSpecifier? = null,
        year: Int? = null,
        sessionName: String? = null,
        sessionType: String? = null
    ): Result<List<RaceSession>> = asResult {
        invoke(sessionKey, meetingKey, year, sessionName, sessionType)
    }
}
