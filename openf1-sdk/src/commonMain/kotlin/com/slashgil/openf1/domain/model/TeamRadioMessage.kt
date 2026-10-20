package com.slashgil.openf1.domain.model

import kotlinx.datetime.Instant

data class TeamRadioMessage(
    val driverNumber: Int?,
    val recordingUrl: String?,
    val sessionKey: Int?,
    val meetingKey: Int?,
    val date: Instant?
)
