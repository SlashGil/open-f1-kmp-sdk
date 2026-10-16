package org.openf1.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PitStop(
    @SerialName("date") val date: String? = null,
    @SerialName("driver_number") val driverNumber: Int? = null,
    @SerialName("lap_number") val lapNumber: Int? = null,
    @SerialName("meeting_key") val meetingKey: Int? = null,
    @SerialName("pit_duration") val pitDuration: Double? = null,
    @SerialName("session_key") val sessionKey: Int? = null,
    @SerialName("stop_number") val stopNumber: Int? = null
)
