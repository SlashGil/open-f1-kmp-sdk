package com.slashgil.openf1.domain.model

import com.slashgil.openf1.model.ChampionshipTeam

data class TeamStanding(
    val teamName: String?,
    val meetingKey: Int?,
    val sessionKey: Int?,
    val pointsCurrent: Double?,
    val pointsStart: Double?,
    val positionCurrent: Int?,
    val positionStart: Int?
) {
    companion object {
        fun from(dto: ChampionshipTeam): TeamStanding = TeamStanding(
            teamName = dto.teamName,
            meetingKey = dto.meetingKey,
            sessionKey = dto.sessionKey,
            pointsCurrent = dto.pointsCurrent,
            pointsStart = dto.pointsStart,
            positionCurrent = dto.positionCurrent,
            positionStart = dto.positionStart
        )
    }
}
