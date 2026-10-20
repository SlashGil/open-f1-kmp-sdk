package com.slashgil.openf1.domain.model

data class TeamStanding(
    val teamName: String?,
    val meetingKey: Int?,
    val sessionKey: Int?,
    val pointsCurrent: Double?,
    val pointsStart: Double?,
    val positionCurrent: Int?,
    val positionStart: Int?
)
