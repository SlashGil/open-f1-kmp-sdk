package com.slashgil.openf1.domain.model

import kotlinx.datetime.Instant

data class CarLocation(
    val driverNumber: Int?,
    val sessionKey: Int?,
    val meetingKey: Int?,
    val x: Int?,
    val y: Int?,
    val z: Int?,
    val date: Instant?
)
