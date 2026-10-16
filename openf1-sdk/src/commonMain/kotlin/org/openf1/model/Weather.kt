package org.openf1.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Weather(
    @SerialName("air_temperature") val airTemperature: Double? = null,
    @SerialName("date") val date: String? = null,
    @SerialName("humidity") val humidity: Double? = null,
    @SerialName("meeting_key") val meetingKey: Int? = null,
    @SerialName("pressure") val pressure: Double? = null,
    @SerialName("rainfall") val rainfall: Int? = null,
    @SerialName("session_key") val sessionKey: Int? = null,
    @SerialName("track_temperature") val trackTemperature: Double? = null,
    @SerialName("wind_direction") val windDirection: Int? = null,
    @SerialName("wind_speed") val windSpeed: Double? = null
)
