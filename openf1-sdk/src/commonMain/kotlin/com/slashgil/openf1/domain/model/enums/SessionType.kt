package com.slashgil.openf1.domain.model.enums

enum class SessionType {
    PRACTICE,
    QUALIFYING,
    SPRINT,
    RACE,
    UNKNOWN;

    companion object {
        fun from(value: String?): SessionType = when {
            value == null -> UNKNOWN
            value.contains("Practice", ignoreCase = true) || value.equals("PRACTICE", ignoreCase = true) -> PRACTICE
            value.contains("Qualifying", ignoreCase = true) || value.equals("QUALIFYING", ignoreCase = true) -> QUALIFYING
            value.contains("Sprint", ignoreCase = true) || value.equals("SPRINT", ignoreCase = true) -> SPRINT
            value.contains("Race", ignoreCase = true) || value.equals("RACE", ignoreCase = true) -> RACE
            else -> UNKNOWN
        }
    }
}
