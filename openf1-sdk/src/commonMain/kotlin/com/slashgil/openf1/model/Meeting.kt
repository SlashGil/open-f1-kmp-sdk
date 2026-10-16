package com.slashgil.openf1.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Meeting(
    @SerialName("circuit_key") val circuitKey: Int? = null,
    @SerialName("circuit_short_name") val circuitShortName: String? = null,
    @SerialName("circuit_type") val circuitType: String? = null,
    @SerialName("country_code") val countryCode: String? = null,
    @SerialName("country_key") val countryKey: Int? = null,
    @SerialName("country_name") val countryName: String? = null,
    @SerialName("date_end") val dateEnd: String? = null,
    @SerialName("date_start") val dateStart: String? = null,
    @SerialName("gmt_offset") val gmtOffset: String? = null,
    @SerialName("is_cancelled") val isCancelled: Boolean? = null,
    @SerialName("location") val location: String? = null,
    @SerialName("meeting_key") val meetingKey: Int? = null,
    @SerialName("meeting_name") val meetingName: String? = null,
    @SerialName("meeting_official_name") val meetingOfficialName: String? = null,
    @SerialName("year") val year: Int? = null
)
