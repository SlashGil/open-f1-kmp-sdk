package com.slashgil.openf1.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Overtake(
    @SerialName("date") val date: String? = null,
    @SerialName("meeting_key") val meetingKey: Int? = null,
    @SerialName("overtaken_driver_number") val overtakenDriverNumber: Int? = null,
    @SerialName("overtaking_driver_number") val overtakingDriverNumber: Int? = null,
    @SerialName("position") val position: Int? = null,
    @SerialName("session_key") val sessionKey: Int? = null
)
