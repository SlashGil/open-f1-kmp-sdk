package com.slashgil.openf1.domain.model

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
)
