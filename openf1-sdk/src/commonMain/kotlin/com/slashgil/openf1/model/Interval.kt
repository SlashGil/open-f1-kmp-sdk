package com.slashgil.openf1.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Interval(
    @SerialName("date") val date: String? = null,
    @SerialName("driver_number") val driverNumber: Int? = null,
    @SerialName("gap_to_leader") val gapToLeader: Double? = null,
    @SerialName("interval") val interval: Double? = null,
    @SerialName("meeting_key") val meetingKey: Int? = null,
    @SerialName("session_key") val sessionKey: Int? = null
)
