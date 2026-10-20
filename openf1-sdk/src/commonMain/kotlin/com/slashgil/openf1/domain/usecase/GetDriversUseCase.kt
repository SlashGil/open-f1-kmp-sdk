package com.slashgil.openf1.domain.usecase

import com.slashgil.openf1.domain.model.DriverProfile
import com.slashgil.openf1.domain.model.specifier.MeetingSpecifier
import com.slashgil.openf1.domain.model.specifier.SessionSpecifier
import com.slashgil.openf1.domain.repository.DriverRepository

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
}
