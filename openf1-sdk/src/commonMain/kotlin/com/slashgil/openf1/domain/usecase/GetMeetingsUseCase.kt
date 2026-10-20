package com.slashgil.openf1.domain.usecase

import com.slashgil.openf1.domain.model.GrandPrixMeeting
import com.slashgil.openf1.domain.model.specifier.MeetingSpecifier
import com.slashgil.openf1.domain.repository.SessionRepository

class GetMeetingsUseCase(
    private val sessionRepository: SessionRepository
) {
    suspend operator fun invoke(
        meetingKey: MeetingSpecifier? = null,
        year: Int? = null,
        countryName: String? = null,
        countryCode: String? = null
    ): List<GrandPrixMeeting> = sessionRepository.getMeetings(
        meetingKey = meetingKey,
        year = year,
        countryName = countryName,
        countryCode = countryCode
    )
}
