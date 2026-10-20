package com.slashgil.openf1.domain.model

import com.slashgil.openf1.model.Position
import kotlinx.datetime.Instant

data class DriverPosition(
    val driverNumber: Int?,
    val position: Int?,
    val sessionKey: Int?,
    val meetingKey: Int?,
    val date: Instant?
) {
    companion object {
        fun from(dto: Position): DriverPosition = DriverPosition(
            driverNumber = dto.driverNumber,
            position = dto.position,
            sessionKey = dto.sessionKey,
            meetingKey = dto.meetingKey,
            date = dto.date.toInstantOrNull()
        )
    }
}
