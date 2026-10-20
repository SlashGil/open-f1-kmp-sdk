package com.slashgil.openf1.domain.model

import com.slashgil.openf1.domain.model.enums.SessionType
import com.slashgil.openf1.model.Session
import kotlinx.datetime.Instant

data class RaceSession(
    val sessionKey: Int?,
    val sessionName: String?,
    val sessionType: SessionType,
    val meetingKey: Int?,
    val circuitKey: Int?,
    val circuitShortName: String?,
    val countryCode: String?,
    val countryKey: Int?,
    val countryName: String?,
    val location: String?,
    val year: Int?,
    val gmtOffset: String?,
    val dateStart: Instant?,
    val dateEnd: Instant?
) {
    companion object {
        fun from(dto: Session): RaceSession = RaceSession(
            sessionKey = dto.sessionKey,
            sessionName = dto.sessionName,
            sessionType = SessionType.from(dto.sessionType ?: dto.sessionName),
            meetingKey = dto.meetingKey,
            circuitKey = dto.circuitKey,
            circuitShortName = dto.circuitShortName,
            countryCode = dto.countryCode,
            countryKey = dto.countryKey,
            countryName = dto.countryName,
            location = dto.location,
            year = dto.year,
            gmtOffset = dto.gmtOffset,
            dateStart = dto.dateStart.toInstantOrNull(),
            dateEnd = dto.dateEnd.toInstantOrNull()
        )
    }
}
