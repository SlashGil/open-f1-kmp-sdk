package com.slashgil.openf1.domain.model

import com.slashgil.openf1.model.CarData
import kotlinx.datetime.Instant

data class CarTelemetry(
    val driverNumber: Int?,
    val sessionKey: Int?,
    val meetingKey: Int?,
    val brake: Int?,
    val drs: Int?,
    val gear: Int?,
    val nGear: Int?,
    val rpm: Int?,
    val speed: Int?,
    val throttle: Int?,
    val date: Instant?
) {
    val isDrsActive: Boolean
        get() = drs == 10 || drs == 12 || drs == 14

    companion object {
        fun from(dto: CarData): CarTelemetry = CarTelemetry(
            driverNumber = dto.driverNumber,
            sessionKey = dto.sessionKey,
            meetingKey = dto.meetingKey,
            brake = dto.brake,
            drs = dto.drs,
            gear = dto.gear,
            nGear = dto.nGear,
            rpm = dto.rpm,
            speed = dto.speed,
            throttle = dto.throttle,
            date = dto.date.toInstantOrNull()
        )
    }
}
