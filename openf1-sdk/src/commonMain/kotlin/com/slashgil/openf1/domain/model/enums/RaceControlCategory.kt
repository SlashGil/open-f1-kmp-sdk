package com.slashgil.openf1.domain.model.enums

enum class RaceControlCategory {
    FLAG,
    SAFETY_CAR,
    INVESTIGATION,
    TRACK_LIMITS,
    OTHER,
    UNKNOWN;

    companion object {
        fun from(value: String?): RaceControlCategory = when {
            value == null -> UNKNOWN
            value.equals("Flag", ignoreCase = true) -> FLAG
            value.contains("SafetyCar", ignoreCase = true) || value.contains("Safety Car", ignoreCase = true) -> SAFETY_CAR
            value.contains("Investigation", ignoreCase = true) -> INVESTIGATION
            value.contains("TrackLimits", ignoreCase = true) || value.contains("Track Limits", ignoreCase = true) -> TRACK_LIMITS
            else -> OTHER
        }
    }
}
