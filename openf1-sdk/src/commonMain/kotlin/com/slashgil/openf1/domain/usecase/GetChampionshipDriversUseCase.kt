package com.slashgil.openf1.domain.usecase

import com.slashgil.openf1.domain.model.DriverStanding
import com.slashgil.openf1.domain.model.specifier.MeetingSpecifier
import com.slashgil.openf1.domain.model.specifier.SessionSpecifier
import com.slashgil.openf1.domain.repository.DriverRepository

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
}
