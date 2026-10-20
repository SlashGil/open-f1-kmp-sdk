package com.slashgil.openf1.domain.model

import com.slashgil.openf1.model.Location
import kotlinx.datetime.Instant

data class CarLocation(
    val driverNumber: Int?,
    val sessionKey: Int?,
    val meetingKey: Int?,
    val x: Int?,
    val y: Int?,
    val z: Int?,
    val date: Instant?
) {
    companion object {
        fun from(dto: Location): CarLocation = CarLocation(
            driverNumber = dto.driverNumber,
            sessionKey = dto.sessionKey,
            meetingKey = dto.meetingKey,
            x = dto.x,
            y = dto.y,
            z = dto.z,
            date = dto.date.toInstantOrNull()
        )
    }
}
