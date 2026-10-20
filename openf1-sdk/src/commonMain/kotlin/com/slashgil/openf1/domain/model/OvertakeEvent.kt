package com.slashgil.openf1.domain.model

import kotlinx.datetime.Instant

data class OvertakeEvent(
    val overtakingDriverNumber: Int?,
    val overtakenDriverNumber: Int?,
    val position: Int?,
    val sessionKey: Int?,
    val meetingKey: Int?,
    val date: Instant?
)
