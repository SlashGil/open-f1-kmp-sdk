package com.slashgil.openf1.domain.usecase

import com.slashgil.openf1.domain.model.DriverProfile
import com.slashgil.openf1.domain.model.specifier.MeetingSpecifier
import com.slashgil.openf1.domain.model.specifier.SessionSpecifier
import com.slashgil.openf1.domain.repository.DriverRepository
import com.slashgil.openf1.domain.result.Result
import com.slashgil.openf1.domain.result.asResult

class GetDriversUseCase(
    private val driverRepository: DriverRepository
) {
    suspend operator fun invoke(
        driverNumber: Int? = null,
        sessionKey: SessionSpecifier? = null,
        meetingKey: MeetingSpecifier? = null,
        teamName: String? = null
    ): List<DriverProfile> = driverRepository.getDrivers(
        driverNumber = driverNumber,
        sessionKey = sessionKey,
        meetingKey = meetingKey,
        teamName = teamName
    )

    suspend fun asResult(
        driverNumber: Int? = null,
        sessionKey: SessionSpecifier? = null,
        meetingKey: MeetingSpecifier? = null,
        teamName: String? = null
    ): Result<List<DriverProfile>> = com.slashgil.openf1.domain.result.asResult {
        invoke(driverNumber, sessionKey, meetingKey, teamName)
    }
}
