package com.slashgil.openf1.domain.usecase

import com.slashgil.openf1.domain.model.DriverStanding
import com.slashgil.openf1.domain.model.specifier.MeetingSpecifier
import com.slashgil.openf1.domain.model.specifier.SessionSpecifier
import com.slashgil.openf1.domain.repository.DriverRepository
import com.slashgil.openf1.domain.result.Result
import com.slashgil.openf1.domain.result.asResult

class GetChampionshipDriversUseCase(
    private val driverRepository: DriverRepository
) {
    suspend operator fun invoke(
        driverNumber: Int? = null,
        sessionKey: SessionSpecifier? = null,
        meetingKey: MeetingSpecifier? = null
    ): List<DriverStanding> = driverRepository.getChampionshipDrivers(
        driverNumber = driverNumber,
        sessionKey = sessionKey,
        meetingKey = meetingKey
    )

    suspend fun asResult(
        driverNumber: Int? = null,
        sessionKey: SessionSpecifier? = null,
        meetingKey: MeetingSpecifier? = null
    ): Result<List<DriverStanding>> = com.slashgil.openf1.domain.result.asResult {
        invoke(driverNumber, sessionKey, meetingKey)
    }
}
