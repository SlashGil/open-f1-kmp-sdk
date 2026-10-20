package com.slashgil.openf1.data.repository

import com.slashgil.openf1.contract.OpenF1Client
import com.slashgil.openf1.data.mapper.toDomain
import com.slashgil.openf1.domain.model.CarLocation
import com.slashgil.openf1.domain.model.CarTelemetry
import com.slashgil.openf1.domain.model.specifier.MeetingSpecifier
import com.slashgil.openf1.domain.model.specifier.SessionSpecifier
import com.slashgil.openf1.domain.repository.TelemetryRepository

class DefaultTelemetryRepository(
    private val client: OpenF1Client
) : TelemetryRepository {

    override suspend fun getCarData(
        driverNumber: Int?,
        sessionKey: SessionSpecifier?,
        meetingKey: MeetingSpecifier?
    ): List<CarTelemetry> = client.getCarData(
        driverNumber = driverNumber,
        sessionKey = sessionKey?.toParamString(),
        meetingKey = meetingKey?.toParamString()
    ).map { it.toDomain() }

    override suspend fun getLocation(
        sessionKey: SessionSpecifier?,
        driverNumber: Int?,
        meetingKey: MeetingSpecifier?
    ): List<CarLocation> = client.getLocation(
        sessionKey = sessionKey?.toParamString(),
        driverNumber = driverNumber,
        meetingKey = meetingKey?.toParamString()
    ).map { it.toDomain() }
}
