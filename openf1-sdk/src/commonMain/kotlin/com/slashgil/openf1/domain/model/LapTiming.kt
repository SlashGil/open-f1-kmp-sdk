package com.slashgil.openf1.domain.model

import com.slashgil.openf1.model.Lap
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

    companion object {
        fun from(dto: Lap): LapTiming = LapTiming(
            driverNumber = dto.driverNumber,
            lapNumber = dto.lapNumber,
            sessionKey = dto.sessionKey,
            meetingKey = dto.meetingKey,
            lapDuration = dto.lapDuration,
            durationSector1 = dto.durationSector1,
            durationSector2 = dto.durationSector2,
            durationSector3 = dto.durationSector3,
            i1Speed = dto.i1Speed,
            i2Speed = dto.i2Speed,
            stSpeed = dto.stSpeed,
            isPitOutLap = dto.isPitOutLap,
            segmentsSector1 = dto.segmentsSector1,
            segmentsSector2 = dto.segmentsSector2,
            segmentsSector3 = dto.segmentsSector3,
            dateStart = dto.dateStart.toInstantOrNull()
        )
    }
}
