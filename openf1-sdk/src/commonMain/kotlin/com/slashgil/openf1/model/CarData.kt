package com.slashgil.openf1.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CarData(
    @SerialName("brake") val brake: Int? = null,
    @SerialName("date") val date: String? = null,
    @SerialName("driver_number") val driverNumber: Int? = null,
    @SerialName("drs") val drs: Int? = null,
    @SerialName("gear") val gear: Int? = null,
    @SerialName("meeting_key") val meetingKey: Int? = null,
    @SerialName("n_gear") val nGear: Int? = null,
    @SerialName("rpm") val rpm: Int? = null,
    @SerialName("session_key") val sessionKey: Int? = null,
    @SerialName("speed") val speed: Int? = null,
    @SerialName("throttle") val throttle: Int? = null
)
