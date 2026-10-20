package com.slashgil.openf1.domain.model

import com.slashgil.openf1.domain.model.enums.SessionType
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
)
