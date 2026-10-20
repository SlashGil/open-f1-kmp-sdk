package com.slashgil.openf1.domain.model

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
)
