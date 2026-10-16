package org.openf1.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Location(
    @SerialName("date") val date: String? = null,
    @SerialName("driver_number") val driverNumber: Int? = null,
    @SerialName("meeting_key") val meetingKey: Int? = null,
    @SerialName("session_key") val sessionKey: Int? = null,
    @SerialName("x") val x: Int? = null,
    @SerialName("y") val y: Int? = null,
    @SerialName("z") val z: Int? = null
)
