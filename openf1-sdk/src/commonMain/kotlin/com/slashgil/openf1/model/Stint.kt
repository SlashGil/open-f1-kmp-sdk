package com.slashgil.openf1.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Stint(
    @SerialName("compound") val compound: String? = null,
    @SerialName("driver_number") val driverNumber: Int? = null,
    @SerialName("lap_end") val lapEnd: Int? = null,
    @SerialName("lap_start") val lapStart: Int? = null,
    @SerialName("meeting_key") val meetingKey: Int? = null,
    @SerialName("session_key") val sessionKey: Int? = null,
    @SerialName("stint_number") val stintNumber: Int? = null,
    @SerialName("tyre_age_at_start") val tyreAgeAtStart: Int? = null
)
