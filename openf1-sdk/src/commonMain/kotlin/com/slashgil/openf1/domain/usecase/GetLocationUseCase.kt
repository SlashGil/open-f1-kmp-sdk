package com.slashgil.openf1.domain.usecase

import com.slashgil.openf1.domain.model.CarLocation
import com.slashgil.openf1.domain.model.specifier.MeetingSpecifier
import com.slashgil.openf1.domain.model.specifier.SessionSpecifier
import com.slashgil.openf1.domain.repository.TelemetryRepository
import com.slashgil.openf1.domain.result.Result
import com.slashgil.openf1.domain.result.asResult

class GetLocationUseCase(
    private val telemetryRepository: TelemetryRepository
) {
    suspend operator fun invoke(
        sessionKey: SessionSpecifier? = null,
        driverNumber: Int? = null,
        meetingKey: MeetingSpecifier? = null
    ): List<CarLocation> = telemetryRepository.getLocation(
        sessionKey = sessionKey,
        driverNumber = driverNumber,
        meetingKey = meetingKey
    )

    suspend fun asResult(
        sessionKey: SessionSpecifier? = null,
        driverNumber: Int? = null,
        meetingKey: MeetingSpecifier? = null
    ): Result<List<CarLocation>> = asResult {
        invoke(sessionKey, driverNumber, meetingKey)
    }
}
