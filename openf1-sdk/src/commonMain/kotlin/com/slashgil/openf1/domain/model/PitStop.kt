package com.slashgil.openf1.domain.model

import kotlinx.datetime.Instant

data class PitStop(
    val driverNumber: Int?,
    val lapNumber: Int?,
    val stopNumber: Int?,
    val pitDuration: Double?,
    val sessionKey: Int?,
    val meetingKey: Int?,
    val date: Instant?
)
