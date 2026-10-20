package com.slashgil.openf1.domain.usecase

import com.slashgil.openf1.domain.model.RaceControlMessage
import com.slashgil.openf1.domain.model.specifier.MeetingSpecifier
import com.slashgil.openf1.domain.model.specifier.SessionSpecifier
import com.slashgil.openf1.domain.repository.RaceEventRepository
import com.slashgil.openf1.domain.result.Result
import com.slashgil.openf1.domain.result.asResult

class GetRaceControlUseCase(
    private val raceEventRepository: RaceEventRepository
) {
    suspend operator fun invoke(
        sessionKey: SessionSpecifier? = null,
        driverNumber: Int? = null,
        meetingKey: MeetingSpecifier? = null,
        flag: String? = null,
        category: String? = null
    ): List<RaceControlMessage> = raceEventRepository.getRaceControl(
        sessionKey = sessionKey,
        driverNumber = driverNumber,
        meetingKey = meetingKey,
        flag = flag,
        category = category
    )

    suspend fun asResult(
        sessionKey: SessionSpecifier? = null,
        driverNumber: Int? = null,
        meetingKey: MeetingSpecifier? = null,
        flag: String? = null,
        category: String? = null
    ): Result<List<RaceControlMessage>> = asResult {
        invoke(sessionKey, driverNumber, meetingKey, flag, category)
    }
}
