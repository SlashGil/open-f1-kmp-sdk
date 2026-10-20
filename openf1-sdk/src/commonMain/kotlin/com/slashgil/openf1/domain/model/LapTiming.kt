package com.slashgil.openf1.domain.model

import kotlinx.datetime.Instant

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
            val mins = (seconds / 60).toInt()
            val remSecs = seconds % 60
            return if (mins > 0) {
                val secStr = if (remSecs < 10) "0${remSecs.formatThreeDecimals()}" else remSecs.formatThreeDecimals()
                "$mins:$secStr"
            } else {
                remSecs.formatThreeDecimals()
            }
        }

    private fun Double.formatThreeDecimals(): String {
        val intPart = this.toInt()
        val fracPart = ((this - intPart) * 1000).toInt()
        val fracStr = when {
            fracPart < 10 -> "00$fracPart"
            fracPart < 100 -> "0$fracPart"
            else -> "$fracPart"
        }
        return "$intPart.$fracStr"
    }
}
