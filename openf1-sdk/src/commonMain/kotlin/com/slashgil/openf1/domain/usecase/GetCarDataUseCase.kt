package com.slashgil.openf1.domain.usecase

import com.slashgil.openf1.domain.model.CarTelemetry
import com.slashgil.openf1.domain.model.specifier.MeetingSpecifier
import com.slashgil.openf1.domain.model.specifier.SessionSpecifier
import com.slashgil.openf1.domain.repository.TelemetryRepository

class GetCarDataUseCase(
    private val telemetryRepository: TelemetryRepository
) {
    suspend operator fun invoke(
        driverNumber: Int? = null,
        sessionKey: SessionSpecifier? = null,
        meetingKey: MeetingSpecifier? = null
    ): List<CarTelemetry> = telemetryRepository.getCarData(
        driverNumber = driverNumber,
        sessionKey = sessionKey,
        meetingKey = meetingKey
    )
}
