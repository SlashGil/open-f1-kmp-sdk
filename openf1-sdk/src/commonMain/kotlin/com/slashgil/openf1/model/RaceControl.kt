package com.slashgil.openf1.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RaceControl(
    @SerialName("category") val category: String? = null,
    @SerialName("date") val date: String? = null,
    @SerialName("driver_number") val driverNumber: Int? = null,
    @SerialName("flag") val flag: String? = null,
    @SerialName("lap_number") val lapNumber: Int? = null,
    @SerialName("meeting_key") val meetingKey: Int? = null,
    @SerialName("message") val message: String? = null,
    @SerialName("qualifying_phase") val qualifyingPhase: String? = null,
    @SerialName("scope") val scope: String? = null,
    @SerialName("sector") val sector: Int? = null,
    @SerialName("session_key") val sessionKey: Int? = null
)
