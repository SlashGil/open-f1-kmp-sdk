package com.slashgil.openf1.domain.model

data class DriverStanding(
    val driverNumber: Int?,
    val meetingKey: Int?,
    val sessionKey: Int?,
    val pointsCurrent: Double?,
    val pointsStart: Double?,
    val positionCurrent: Int?,
    val positionStart: Int?
)
