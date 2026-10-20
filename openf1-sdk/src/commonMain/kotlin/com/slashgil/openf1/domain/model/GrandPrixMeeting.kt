package com.slashgil.openf1.domain.model

import com.slashgil.openf1.model.Meeting
import kotlinx.datetime.Instant

data class GrandPrixMeeting(
    val meetingKey: Int?,
    val meetingName: String?,
    val meetingOfficialName: String?,
    val circuitKey: Int?,
    val circuitShortName: String?,
    val circuitType: String?,
    val countryCode: String?,
    val countryKey: Int?,
    val countryName: String?,
    val gmtOffset: String?,
    val location: String?,
    val year: Int?,
    val isCancelled: Boolean?,
    val dateStart: Instant?,
    val dateEnd: Instant?
) {
    companion object {
        fun from(dto: Meeting): GrandPrixMeeting = GrandPrixMeeting(
            meetingKey = dto.meetingKey,
            meetingName = dto.meetingName,
            meetingOfficialName = dto.meetingOfficialName,
            circuitKey = dto.circuitKey,
            circuitShortName = dto.circuitShortName,
            circuitType = dto.circuitType,
            countryCode = dto.countryCode,
            countryKey = dto.countryKey,
            countryName = dto.countryName,
            gmtOffset = dto.gmtOffset,
            location = dto.location,
            year = dto.year,
            isCancelled = dto.isCancelled,
            dateStart = dto.dateStart.toInstantOrNull(),
            dateEnd = dto.dateEnd.toInstantOrNull()
        )
    }
}
