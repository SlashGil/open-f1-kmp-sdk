package com.slashgil.openf1.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Session(
    @SerialName("circuit_key") val circuitKey: Int? = null,
    @SerialName("circuit_short_name") val circuitShortName: String? = null,
    @SerialName("country_code") val countryCode: String? = null,
    @SerialName("country_key") val countryKey: Int? = null,
    @SerialName("country_name") val countryName: String? = null,
    @SerialName("date_end") val dateEnd: String? = null,
    @SerialName("date_start") val dateStart: String? = null,
    @SerialName("gmt_offset") val gmtOffset: String? = null,
    @SerialName("location") val location: String? = null,
    @SerialName("meeting_key") val meetingKey: Int? = null,
    @SerialName("session_key") val sessionKey: Int? = null,
    @SerialName("session_name") val sessionName: String? = null,
    @SerialName("session_type") val sessionType: String? = null,
    @SerialName("year") val year: Int? = null
)
