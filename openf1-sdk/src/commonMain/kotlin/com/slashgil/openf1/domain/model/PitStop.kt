package com.slashgil.openf1.domain.model

import com.slashgil.openf1.model.PitStop as PitStopDto
import kotlinx.datetime.Instant

data class PitStop(
    val driverNumber: Int?,
    val lapNumber: Int?,
    val stopNumber: Int?,
    val pitDuration: Double?,
    val sessionKey: Int?,
    val meetingKey: Int?,
    val date: Instant?
) {
    companion object {
        fun from(dto: PitStopDto): PitStop = PitStop(
            driverNumber = dto.driverNumber,
            lapNumber = dto.lapNumber,
            stopNumber = dto.stopNumber,
            pitDuration = dto.pitDuration,
            sessionKey = dto.sessionKey,
            meetingKey = dto.meetingKey,
            date = dto.date.toInstantOrNull()
        )
    }
}
