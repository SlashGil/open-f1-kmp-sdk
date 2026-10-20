package com.slashgil.openf1.domain.model

import com.slashgil.openf1.model.Overtake
import kotlinx.datetime.Instant

data class OvertakeEvent(
    val overtakingDriverNumber: Int?,
    val overtakenDriverNumber: Int?,
    val position: Int?,
    val sessionKey: Int?,
    val meetingKey: Int?,
    val date: Instant?
) {
    companion object {
        fun from(dto: Overtake): OvertakeEvent = OvertakeEvent(
            overtakingDriverNumber = dto.overtakingDriverNumber,
            overtakenDriverNumber = dto.overtakenDriverNumber,
            position = dto.position,
            sessionKey = dto.sessionKey,
            meetingKey = dto.meetingKey,
            date = dto.date.toInstantOrNull()
        )
    }
}
