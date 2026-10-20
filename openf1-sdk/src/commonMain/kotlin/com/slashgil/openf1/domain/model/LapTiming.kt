package com.slashgil.openf1.domain.model

import kotlinx.datetime.Instant
import kotlin.math.roundToInt

data class LapTiming(
    val driverNumber: Int?,
    val lapNumber: Int?,
    val sessionKey: Int?,
    val meetingKey: Int?,
    val lapDuration: Double?,
    val durationSector1: Double?,
    val durationSector2: Double?,
    val durationSector3: Double?,
    val i1Speed: Int?,
    val i2Speed: Int?,
    val stSpeed: Int?,
    val isPitOutLap: Boolean?,
    val segmentsSector1: List<Int>?,
    val segmentsSector2: List<Int>?,
    val segmentsSector3: List<Int>?,
    val dateStart: Instant?
) {
    val formattedLapDuration: String?
        get() {
            val seconds = lapDuration ?: return null
            val totalMillis = (seconds * 1000).roundToInt()
            val mins = totalMillis / 60000
            val remMillis = totalMillis % 60000
            val secs = remMillis / 1000
            val fracPart = remMillis % 1000
            val fracStr = when {
                fracPart < 10 -> "00$fracPart"
                fracPart < 100 -> "0$fracPart"
                else -> "$fracPart"
            }
            val secStr = if (mins > 0 && secs < 10) "0$secs" else "$secs"
            return if (mins > 0) {
                "$mins:$secStr.$fracStr"
            } else {
                "$secs.$fracStr"
            }
        }
}
