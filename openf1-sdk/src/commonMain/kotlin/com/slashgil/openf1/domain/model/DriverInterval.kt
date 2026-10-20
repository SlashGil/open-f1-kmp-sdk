package com.slashgil.openf1.domain.model

import com.slashgil.openf1.model.Interval
import kotlinx.datetime.Instant

data class DriverInterval(
    val driverNumber: Int?,
    val sessionKey: Int?,
    val meetingKey: Int?,
    val gapToLeader: Double?,
    val interval: Double?,
    val date: Instant?
) {
    companion object {
        fun from(dto: Interval): DriverInterval = DriverInterval(
            driverNumber = dto.driverNumber,
            sessionKey = dto.sessionKey,
            meetingKey = dto.meetingKey,
            gapToLeader = dto.gapToLeader,
            interval = dto.interval,
            date = dto.date.toInstantOrNull()
        )
    }
}
