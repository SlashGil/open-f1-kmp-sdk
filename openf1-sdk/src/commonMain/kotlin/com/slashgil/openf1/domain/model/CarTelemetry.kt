package com.slashgil.openf1.domain.model

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
}
