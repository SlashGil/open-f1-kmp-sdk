package com.slashgil.openf1.domain.model

import com.slashgil.openf1.model.Pit
import kotlinx.datetime.Instant

data class PitLaneEntry(
    val driverNumber: Int?,
    val lapNumber: Int?,
    val laneDuration: Double?,
    val stopDuration: Double?,
    val pitDuration: Double?,
    val sessionKey: Int?,
    val meetingKey: Int?,
    val date: Instant?
) {
    companion object {
        fun from(dto: Pit): PitLaneEntry = PitLaneEntry(
            driverNumber = dto.driverNumber,
            lapNumber = dto.lapNumber,
            laneDuration = dto.laneDuration,
            stopDuration = dto.stopDuration,
            pitDuration = dto.pitDuration,
            sessionKey = dto.sessionKey,
            meetingKey = dto.meetingKey,
            date = dto.date.toInstantOrNull()
        )
    }
}
