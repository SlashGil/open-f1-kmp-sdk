package org.openf1.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ChampionshipDriver(
    @SerialName("driver_number") val driverNumber: Int? = null,
    @SerialName("meeting_key") val meetingKey: Int? = null,
    @SerialName("points_current") val pointsCurrent: Double? = null,
    @SerialName("points_start") val pointsStart: Double? = null,
    @SerialName("position_current") val positionCurrent: Int? = null,
    @SerialName("position_start") val positionStart: Int? = null,
    @SerialName("session_key") val sessionKey: Int? = null
)
