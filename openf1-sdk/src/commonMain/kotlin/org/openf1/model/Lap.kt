package org.openf1.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Lap(
    @SerialName("date_start") val dateStart: String? = null,
    @SerialName("driver_number") val driverNumber: Int? = null,
    @SerialName("duration_sector_1") val durationSector1: Double? = null,
    @SerialName("duration_sector_2") val durationSector2: Double? = null,
    @SerialName("duration_sector_3") val durationSector3: Double? = null,
    @SerialName("i1_speed") val i1Speed: Int? = null,
    @SerialName("i2_speed") val i2Speed: Int? = null,
    @SerialName("is_pit_out_lap") val isPitOutLap: Boolean? = null,
    @SerialName("lap_duration") val lapDuration: Double? = null,
    @SerialName("lap_number") val lapNumber: Int? = null,
    @SerialName("meeting_key") val meetingKey: Int? = null,
    @SerialName("segments_sector_1") val segmentsSector1: List<Int>? = null,
    @SerialName("segments_sector_2") val segmentsSector2: List<Int>? = null,
    @SerialName("segments_sector_3") val segmentsSector3: List<Int>? = null,
    @SerialName("session_key") val sessionKey: Int? = null,
    @SerialName("st_speed") val stSpeed: Int? = null
)
