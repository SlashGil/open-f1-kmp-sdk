package com.slashgil.openf1.domain.model

import com.slashgil.openf1.model.TeamRadio
import kotlinx.datetime.Instant

data class TeamRadioMessage(
    val driverNumber: Int?,
    val recordingUrl: String?,
    val sessionKey: Int?,
    val meetingKey: Int?,
    val date: Instant?
) {
    companion object {
        fun from(dto: TeamRadio): TeamRadioMessage = TeamRadioMessage(
            driverNumber = dto.driverNumber,
            recordingUrl = dto.recordingUrl,
            sessionKey = dto.sessionKey,
            meetingKey = dto.meetingKey,
            date = dto.date.toInstantOrNull()
        )
    }
}
