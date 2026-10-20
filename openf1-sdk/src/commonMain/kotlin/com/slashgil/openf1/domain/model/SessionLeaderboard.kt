package com.slashgil.openf1.domain.model

data class SessionLeaderboardEntry(
    val position: Int?,
    val driver: DriverProfile?,
    val gapToLeader: Double?,
    val interval: Double?,
    val bestLap: LapTiming?
)

data class SessionLeaderboard(
    val sessionKey: Int?,
    val entries: List<SessionLeaderboardEntry>
)
