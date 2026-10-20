package com.slashgil.openf1.domain.repository

import com.slashgil.openf1.domain.model.CarLocation
import com.slashgil.openf1.domain.model.CarTelemetry
import com.slashgil.openf1.domain.model.specifier.MeetingSpecifier
import com.slashgil.openf1.domain.model.specifier.SessionSpecifier

interface TelemetryRepository {
    suspend fun getCarData(
        driverNumber: Int? = null,
        sessionKey: SessionSpecifier? = null,
        meetingKey: MeetingSpecifier? = null
    ): List<CarTelemetry>

    suspend fun getLocation(
        sessionKey: SessionSpecifier? = null,
        driverNumber: Int? = null,
        meetingKey: MeetingSpecifier? = null
    ): List<CarLocation>
}
