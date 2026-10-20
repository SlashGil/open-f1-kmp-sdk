package com.slashgil.openf1.domain.model

import kotlinx.datetime.Instant

data class DriverInterval(
    val driverNumber: Int?,
    val sessionKey: Int?,
    val meetingKey: Int?,
    val gapToLeader: Double?,
    val interval: Double?,
    val date: Instant?
)
