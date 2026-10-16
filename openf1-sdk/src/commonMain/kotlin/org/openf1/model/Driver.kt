package org.openf1.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Driver(
    @SerialName("broadcast_name") val broadcastName: String? = null,
    @SerialName("country_code") val countryCode: String? = null,
    @SerialName("driver_number") val driverNumber: Int? = null,
    @SerialName("first_name") val firstName: String? = null,
    @SerialName("full_name") val fullName: String? = null,
    @SerialName("headshot_url") val headshotUrl: String? = null,
    @SerialName("last_name") val lastName: String? = null,
    @SerialName("meeting_key") val meetingKey: Int? = null,
    @SerialName("name_acronym") val nameAcronym: String? = null,
    @SerialName("session_key") val sessionKey: Int? = null,
    @SerialName("team_colour") val teamColour: String? = null,
    @SerialName("team_name") val teamName: String? = null
)
